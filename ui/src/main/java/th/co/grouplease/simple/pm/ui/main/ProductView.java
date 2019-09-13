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
import org.springframework.beans.factory.annotation.Autowired;
import th.co.grouplease.simple.pm.ui.component.PMMenuBar;
import th.co.grouplease.simple.pm.ui.dataprovider.ProductReleaseDataProvider;
import th.co.grouplease.simple.pm.ui.model.Product;
import th.co.grouplease.simple.pm.ui.model.ProductRelease;
import th.co.grouplease.simple.pm.ui.service.ProductService;

@Route("")
@PWA(name = "Simple Project Management Tool", shortName = "PM")
public class ProductView extends VerticalLayout {

    public ProductView(@Autowired ProductService productService, @Autowired ProductReleaseDataProvider productReleaseDataProvider) {
        add(new PMMenuBar());
        configureProductGrid(productService, productReleaseDataProvider);
        configureProductReleaseGrid(productReleaseDataProvider);
    }

    private void configureProductGrid(ProductService productService, ProductReleaseDataProvider productReleaseDataProvider) {
        add(new H2("Products"));
        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidthFull();
        add(horizontalLayout);

        var productGrid = new Grid<>(Product.class);
        productGrid.setWidthFull();
        productGrid.setColumns("name", "productStartDate", "productEndDate");
        horizontalLayout.add(productGrid);

        FormLayout productFormLayout = new FormLayout();
        productFormLayout.setWidthFull();
        var nameTextField = new TextField("Name");
        var productStartDateField = new DatePicker("Product start date");
        var productEndDateField = new DatePicker("Product end date");
        var createProductButton = new Button("Create");
        createProductButton.setEnabled(false);
        createProductButton.setDisableOnClick(true);

        productFormLayout.add(nameTextField);
        productFormLayout.add(productStartDateField);
        productFormLayout.add(productEndDateField);

        // Wrap form and button in vertical layout
        var rootFormLayout = new VerticalLayout();
        rootFormLayout.setWidthFull();
        horizontalLayout.add(rootFormLayout);
        rootFormLayout.add(productFormLayout);
        rootFormLayout.add(createProductButton);

        // Add data binder
        var productDataBinder = new BeanValidationBinder<>(Product.class);
        productDataBinder.forField(nameTextField).bind("name");
        productDataBinder.forField(productStartDateField).bind("productStartDate");
        productDataBinder.forField(productEndDateField).bind("productEndDate");
        productDataBinder.setBean(new Product());

        // Add create listener
        productDataBinder.addStatusChangeListener(statusChangeEvent -> createProductButton.setEnabled(statusChangeEvent.getBinder().isValid()));


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
        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidthFull();
        add(horizontalLayout);

        var productReleaseGrid = new Grid<>(ProductRelease.class);
        productReleaseGrid.setWidthFull();
        productReleaseGrid.setColumns("version", "releaseDate");
        productReleaseGrid.setDataProvider(productReleaseDataProvider);
        horizontalLayout.add(productReleaseGrid);

        FormLayout productFormLayout = new FormLayout();
        productFormLayout.setWidthFull();
        var versionTextField = new TextField("Version");
        var releaseDateField = new DatePicker("Release date");

        productFormLayout.add(versionTextField);
        productFormLayout.add(releaseDateField);

        var createProductButton = new Button("Create");
        createProductButton.setEnabled(false);

        // Wrap form and button in vertical layout
        var rootFormLayout = new VerticalLayout();
        rootFormLayout.setWidthFull();
        horizontalLayout.add(rootFormLayout);
        rootFormLayout.add(productFormLayout);
        rootFormLayout.add(createProductButton);
    }

}