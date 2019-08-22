package th.co.grouplease.simple.pm.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import th.co.grouplease.simple.pm.project.Project;
import th.co.grouplease.simple.pm.project.ProjectRepository;

import javax.validation.constraints.Min;

@RestController
@Validated
@RequestMapping(path = "/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductReleaseRepository productReleaseRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public Page<Product> getAllProducts(@RequestParam @Min(value = 0, message = "Page must be at least 0") int page,
                                        @RequestParam @Min(value = 1, message = "Page size must be at least 1") int pageSize){
        return productRepository.findAll(PageRequest.of(page, pageSize));
    }

    @GetMapping(path = "/count")
    public Long getCount(){
        return productRepository.count();
    }

    @GetMapping(path = "/{productId}/releases")
    public Page<ProductRelease> getAllReleasesForProduct(@PathVariable Long productId,
                                                         @RequestParam @Min(value = 0, message = "Page must be at least 0") int page,
                                                         @RequestParam @Min(value = 1, message = "Page size must be at least 1") int pageSize){
        return productReleaseRepository.findAllByProduct(
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
                PageRequest.of(page, pageSize)
        );
    }

    @GetMapping(path = "/{productId}/releases/count")
    public Long getReleaseCountForProduct(@PathVariable Long productId){
        return productReleaseRepository.countAllByProduct(
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    @GetMapping(path = "/{productId}/projects")
    public Page<Project> getAllProjectsForProduct(@PathVariable Long productId,
                                                  @RequestParam @Min(value = 0, message = "Page must be at least 0") int page,
                                                  @RequestParam @Min(value = 1, message = "Page size must be at least 1") int pageSize){
        return projectRepository.findAllByProduct(
                productRepository.findById(productId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
                PageRequest.of(page, pageSize)
        );
    }

    @GetMapping(path = "/{productId}/projects/count")
    public Long getProjectCountForProduct(@PathVariable Long productId){
        return projectRepository.countAllByProduct(
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }
}
