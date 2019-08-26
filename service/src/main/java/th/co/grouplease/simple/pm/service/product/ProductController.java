package th.co.grouplease.simple.pm.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import th.co.grouplease.simple.pm.service.product.domain.model.Product;
import th.co.grouplease.simple.pm.service.product.domain.model.ProductRelease;
import th.co.grouplease.simple.pm.service.product.repository.ProductReleaseRepository;
import th.co.grouplease.simple.pm.service.product.repository.ProductRepository;
import th.co.grouplease.simple.pm.service.project.read.model.ProjectEntry;
import th.co.grouplease.simple.pm.service.project.repository.ProjectEntryRepository;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@Validated
@RequestMapping
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductReleaseRepository productReleaseRepository;
    @Autowired
    private ProjectEntryRepository projectEntryRepository;

    // product endpoints
    @GetMapping(path = "/products")
    public Page<Product> getAllProducts(@RequestParam @Min(value = 0, message = "Page must be at least 0") int page,
                                        @RequestParam @Min(value = 1, message = "Page size must be at least 1") int pageSize){
        return productRepository.findAll(PageRequest.of(page, pageSize));
    }

    @PostMapping(path = "/products")
    public Product createProduct(@RequestBody @Valid Product product){
        return productRepository.save(product);
    }

    @PutMapping(path = "/products/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody @Valid Product product){
        if(!productId.equals(product.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "productId is not equal to product.productId");
        }

        if(productRepository.existsById(product.getId())){
            return productRepository.save(product);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/products/{productId}")
    public void deleteProduct(@PathVariable Long productId){
        if(productRepository.existsById(productId)){
            productRepository.deleteById(productId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/products/count")
    public Long getCount(){
        return productRepository.count();
    }

    @GetMapping(path = "/products/{productId}")
    public Product getProductById(@PathVariable Long productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // productRelease endpoints
    @PostMapping(path = "/products/{productId}/releases")
    public ProductRelease createProductRelease(@PathVariable Long productId, @RequestBody @Valid ProductRelease productRelease){
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find productId: " + productId));
        productRelease.setProduct(product);
        return productReleaseRepository.save(productRelease);
    }

    @PutMapping(path = "/products/{productId}/releases/{releaseId}")
    public ProductRelease updateProductRelease(@PathVariable Long productId,
                                               @PathVariable Long releaseId,
                                               @RequestBody @Valid ProductRelease productRelease){
        if(productReleaseRepository.existsById(releaseId)) {
            var product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find productId: " + productId));
            productRelease.setProduct(product);
            return productReleaseRepository.save(productRelease);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find releaseId: " + releaseId);
        }
    }

    @DeleteMapping(path = "/releases/{releaseId}")
    public void deleteProductRelease(@PathVariable Long releaseId){
        if(productReleaseRepository.existsById(releaseId)){
            productReleaseRepository.deleteById(releaseId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/products/{productId}/releases")
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

    @GetMapping(path = "/products/{productId}/releases/count")
    public Long getReleaseCountForProduct(@PathVariable Long productId){
        return productReleaseRepository.countAllByProduct(
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    @GetMapping(path = "/releases/{releaseId}")
    public ProductRelease getReleaseByReleaseId(@PathVariable Long releaseId){
        return productReleaseRepository.findById(releaseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/releases")
    public Page<ProductRelease> getAllReleases(@RequestParam @Min(value = 0, message = "Page must be at least 0") int page,
                                               @RequestParam @Min(value = 1, message = "Page size must be at least 1") int pageSize){
        return productReleaseRepository.findAll(PageRequest.of(page, pageSize));
    }

    // project endpoints
    @GetMapping(path = "/products/{productId}/projects")
    public Page<ProjectEntry> getAllProjectsForProduct(@PathVariable Long productId,
                                                       @RequestParam @Min(value = 0, message = "Page must be at least 0") int page,
                                                       @RequestParam @Min(value = 1, message = "Page size must be at least 1") int pageSize){
        return projectEntryRepository.findAllByProduct(
                productRepository.findById(productId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
                PageRequest.of(page, pageSize)
        );
    }

    @GetMapping(path = "/products/{productId}/projects/count")
    public Long getProjectCountForProduct(@PathVariable Long productId){
        return projectEntryRepository.countAllByProduct(
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }
}
