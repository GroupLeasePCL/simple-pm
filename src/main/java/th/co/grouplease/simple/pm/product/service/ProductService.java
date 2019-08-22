package th.co.grouplease.simple.pm.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import th.co.grouplease.simple.pm.product.Product;
import th.co.grouplease.simple.pm.product.ProductRepository;
import th.co.grouplease.simple.pm.product.command.ChangeProductNameCommand;
import th.co.grouplease.simple.pm.product.command.ChangeProductTimelineCommand;
import th.co.grouplease.simple.pm.product.command.CreateProductCommand;

import javax.validation.Valid;

@Service
@Validated
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(CreateProductCommand createProductCommand){
        return productRepository.save(
                Product.create(createProductCommand.getName(),
                        createProductCommand.getProductStartDate())
                        .withEndDate(createProductCommand.getProductEndDate())
        );
    }

    public void deleteProduct(Long productId) {
        if(!productRepository.existsById(productId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            // Change to soft deleting
            productRepository.deleteById(productId);
        }
    }

    public Product changeProductName(Long productId, @Valid ChangeProductNameCommand command) {
        var productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        productEntity.setName(command.getName());

        return productRepository.save(productEntity);
    }

    public Product changeProductTimeline(Long productId, @Valid ChangeProductTimelineCommand command) {
        var productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        productEntity.setProductStartDate(command.getProductStartDate());
        productEntity.setProductEndDate(command.getProductEndDate());

        return productRepository.save(productEntity);
    }
}
