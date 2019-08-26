package th.co.grouplease.simple.pm.ui.main;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

@Route
@PWA(name = "Simple Project Management Tool", shortName = "PM")
public class MainView extends VerticalLayout {
    @Autowired
    public MainView(ProductService productService) {
        add(new H2("Products"));
        var productGrid = new Grid<>(Product.class);
        CallbackDataProvider<Product, Void> productDataProvider = DataProvider
                .fromCallbacks(query -> productService
                                .getProducts(query.getOffset(), query.getLimit()).stream(),
                        query -> productService.getProductCount());
        productGrid.setDataProvider(productDataProvider);
        add(productGrid);

        add(new H2("Product Releases"));
        var productReleaseGrid = new Grid<>(ProductRelease.class);
        add(productReleaseGrid);
    }

}