package th.co.grouplease.simple.pm.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public List<ProductRelease> getAllReleasesForProduct(@PathVariable Long productId){
        return productReleaseRepository.findAllByProduct(
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    @GetMapping(path = "/{productId}/projects")
    public List<Project> getAllProjectsForProduct(@PathVariable Long productId){
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return projectRepository.findAllByProduct(product);
    }
}
