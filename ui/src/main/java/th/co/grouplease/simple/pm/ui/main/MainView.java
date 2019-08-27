package th.co.grouplease.simple.pm.ui.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import th.co.grouplease.simple.pm.ui.dataprovider.ProductReleaseDataProvider;
import th.co.grouplease.simple.pm.ui.model.Product;
import th.co.grouplease.simple.pm.ui.model.ProductRelease;
import th.co.grouplease.simple.pm.ui.service.ProductService;

@Route
@PWA(name = "Simple Project Management Tool", shortName = "PM")
public class MainView extends VerticalLayout {
    private final Logger logger = LoggerFactory.getLogger(MainView.class);

    @Autowired
    public MainView(ProductService productService, ProductReleaseDataProvider productReleaseDataProvider) {
        configureProductGrid(productService, productReleaseDataProvider);
        configureProductReleaseGrid(productReleaseDataProvider);
    }

    private void configureProductGrid(ProductService productService, ProductReleaseDataProvider productReleaseDataProvider) {
        add(new H2("Products"));
        var productGrid = new Grid<>(Product.class);
        productGrid.setColumns("name", "productStartDate", "productEndDate");
        productGrid.setWidthFull();

        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(productGrid);
        horizontalLayout.setWidthFull();
        add(horizontalLayout);

        FormLayout productFormLayout = new FormLayout();
        var nameTextField = new TextField("Name");
        var productStartDateField = new DatePicker("Product start date");
        var productEndDateField = new DatePicker("Product end date");
        var createProductButton = new Button("Create");
        createProductButton.setEnabled(false);

        productFormLayout.add(nameTextField);
        productFormLayout.add(productStartDateField);
        productFormLayout.add(productEndDateField);
        productFormLayout.add(createProductButton);
        horizontalLayout.add(productFormLayout);

        // Add data binder
        var productDataBinder = new BeanValidationBinder<>(Product.class);
        productDataBinder.forField(nameTextField).bind("name");
        productDataBinder.forField(productStartDateField).bind("productStartDate");
        productDataBinder.forField(productEndDateField).bind("productEndDate");
        productDataBinder.setBean(new Product());

        // Add create listener
        productDataBinder.addStatusChangeListener(statusChangeEvent ->
                createProductButton.setEnabled(!statusChangeEvent.hasValidationErrors()));

        CallbackDataProvider<Product, Void> productDataProvider = DataProvider
                .fromCallbacks(query -> productService.getProducts(query.getOffset(), query.getLimit()).stream(),
                        query -> productService.getProductCount());
        productGrid.setDataProvider(productDataProvider);

        // logic trigger
        productGrid.addSelectionListener(selectionEvent -> {
            var optionalSelectedItem = selectionEvent.getFirstSelectedItem();
            if(optionalSelectedItem.isPresent()){
                productReleaseDataProvider.setProductId(optionalSelectedItem.get().getId());
            } else {
                productReleaseDataProvider.setProductId(null);
            }
            productReleaseDataProvider.refreshAll();
        });

        createProductButton.addClickListener(buttonClickEvent -> {
            var bean = productDataBinder.getBean();
            productService.createProduct(bean);
            productDataBinder.setBean(new Product());
            Notification.show("Product is created");
            productDataProvider.refreshAll();
        });
    }

    private void configureProductReleaseGrid(ProductReleaseDataProvider productReleaseDataProvider) {
        add(new H2("Product Releases"));
        var productReleaseGrid = new Grid<>(ProductRelease.class);
        productReleaseGrid.setColumns("version", "releaseDate");
        productReleaseGrid.setDataProvider(productReleaseDataProvider);

        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidthFull();
        horizontalLayout.add(productReleaseGrid);
        add(horizontalLayout);
    }

}