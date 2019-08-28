package th.co.grouplease.simple.pm.ui.dataprovider;

import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import th.co.grouplease.simple.pm.ui.model.ProductRelease;
import th.co.grouplease.simple.pm.ui.service.ProductReleaseService;

import java.util.stream.Stream;

@Component
@Scope("prototype")
public class ProductReleaseDataProvider extends AbstractBackEndDataProvider<ProductRelease, Void> {

    private Long productId;
    private final ProductReleaseService productReleaseService;

    public ProductReleaseDataProvider(@Autowired ProductReleaseService productReleaseService){
        this.productReleaseService = productReleaseService;
    }

    @Override
    protected Stream<ProductRelease> fetchFromBackEnd(Query<ProductRelease, Void> query) {
        return productId != null
                ? productReleaseService.getProductReleases(productId, query.getOffset(), query.getLimit()).stream()
                : null;
    }

    @Override
    protected int sizeInBackEnd(Query<ProductRelease, Void> query) {
        return productId != null
                ? productReleaseService.getProductReleaseCount(productId)
                : 0;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
