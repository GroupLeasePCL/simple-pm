package th.co.grouplease.simple.pm.ui.dataprovider;

import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import th.co.grouplease.simple.pm.ui.model.Product;
import th.co.grouplease.simple.pm.ui.service.ProductService;

import java.util.stream.Stream;

@Component
@Scope("prototype")
public class ProductDataProvider extends AbstractBackEndDataProvider<Product, Void> {

    private Long productId;
    private final ProductService productService;

    public ProductDataProvider(@Autowired ProductService productService){
        this.productService = productService;
    }

    @Override
    protected Stream<Product> fetchFromBackEnd(Query<Product, Void> query) {
        return productId != null
                ? productService.getProducts(query.getOffset(), query.getLimit()).stream()
                : null;
    }

    @Override
    protected int sizeInBackEnd(Query<Product, Void> query) {
        return productId != null
                ? productService.getProductCount()
                : 0;
    }
}
