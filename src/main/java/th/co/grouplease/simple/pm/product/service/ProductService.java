package th.co.grouplease.simple.pm.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import th.co.grouplease.simple.pm.product.command.*;
import th.co.grouplease.simple.pm.product.domain.model.Product;
import th.co.grouplease.simple.pm.product.domain.model.ProductRelease;
import th.co.grouplease.simple.pm.product.repository.ProductReleaseRepository;
import th.co.grouplease.simple.pm.product.repository.ProductRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductReleaseRepository productReleaseRepository;

    @Transactional
    public Product createProduct(@Valid CreateProductCommand command){
        return productRepository.save(new Product(command));
    }

    @Transactional
    public void deleteProduct(@NotNull(message = "productId cannot be null") String productId) {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        product.delete(new DeleteProductCommand(productId));
        productRepository.save(product);
    }

    @Transactional
    public void changeProductName(@Valid ChangeProductNameCommand command) {
        var product = productRepository.findById(command.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        product.changeName(command);
        productRepository.save(product);
    }

    @Transactional
    public void changeProductTimeline(@Valid ChangeProductTimelineCommand command) {
        var product = productRepository.findById(command.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        product.changeTimeline(command);
        productRepository.save(product);
    }

    @Transactional
    public void createProductRelease(@Valid CreateProductReleaseCommand command){
        var product = productRepository.findById(command.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find productId: " + command.getProductId()));
        productReleaseRepository.save(new ProductRelease(command, product));
    }

    @Transactional
    public void deleteProductRelease(@NotNull(message = "productReleaseId cannot be null") String productReleaseId){
        var productRelease = productReleaseRepository.findById(productReleaseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        productRelease.delete(new DeleteProductReleaseCommand(productReleaseId));
        productReleaseRepository.save(productRelease);
    }
}
