package th.co.grouplease.simple.pm.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import th.co.grouplease.simple.pm.project.Project;
import th.co.grouplease.simple.pm.project.ProjectRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductReleaseRepository productReleaseRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping(path = "/{productId}/releases")
    public List<ProductRelease> getAllReleasesForProduct(@PathVariable Long productId, @RequestParam int page, @RequestParam int pageSize){
        return productReleaseRepository.findAllByProduct(
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
                PageRequest.of(page, pageSize)
        );
    }

    @GetMapping(path = "/{productId}/releases-count")
    public Long getReleaseCountForProduct(@PathVariable Long productId){
        return productReleaseRepository.countAllByProduct(
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    @GetMapping(path = "/{productId}/projects")
    public List<Project> getAllProjectsForProduct(@PathVariable Long productId, @RequestParam int page, @RequestParam int pageSize){
        return projectRepository.findAllByProduct(
                productRepository.findById(productId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
                PageRequest.of(page, pageSize)
        );
    }

    @GetMapping(path = "/{productId}/projects-count")
    public Long getProjectCountForProduct(@PathVariable Long productId){
        return projectRepository.countAllByProduct(
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }
}
