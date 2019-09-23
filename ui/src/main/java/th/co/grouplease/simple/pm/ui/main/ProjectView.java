package th.co.grouplease.simple.pm.ui.main;

import com.vaadin.flow.component.dialog.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import th.co.grouplease.simple.pm.ui.component.PMMenuBar;
import th.co.grouplease.simple.pm.ui.model.Product;
import th.co.grouplease.simple.pm.ui.model.Project;
import th.co.grouplease.simple.pm.ui.service.ProductService;
import th.co.grouplease.simple.pm.ui.service.ProjectService;

import java.util.List;



@Route("project")
public class ProjectView extends VerticalLayout {
    public ProjectView(@Autowired ProjectService projectService, ProductService productService) {
        add(new PMMenuBar());
        add(new H2("Project"));


        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidthFull();
        add(horizontalLayout);

        var projectGrid = new Grid<>(Project.class);
        projectGrid.setWidthFull();
        projectGrid.setColumns("name", "startDate", "expectedStartDate", "endDate", "expectedEndDate", "status");

        horizontalLayout.add(projectGrid);

        var rootFormLayout = new VerticalLayout();
        rootFormLayout.setWidthFull();
        horizontalLayout.add(rootFormLayout);

        FormLayout projectFormLayout = new FormLayout();
        projectFormLayout.setWidthFull();
        var nameTextField = new TextField("Name");
        var projectStartDate = new DatePicker("Start date");
        var projectEndDate = new DatePicker("End date");
        var projectExpectedStartDate = new DatePicker("Expected start date");
        var projectExpectedEndDate = new DatePicker("Expected end date");

        ComboBox<Product> cbProducts = new ComboBox<>("Product");
        cbProducts.setItemLabelGenerator(Product::getName);
        List<Product> listOfProduct = productService.getProducts(0,productService.getProductCount());
        cbProducts.setItems(listOfProduct);

        var createProjectButton = new Button("Create");

        projectFormLayout.add(nameTextField);
        projectFormLayout.add(projectStartDate);
        projectFormLayout.add(projectEndDate);
        projectFormLayout.add(projectExpectedStartDate);
        projectFormLayout.add(projectExpectedEndDate);
        projectFormLayout.add(cbProducts);

        rootFormLayout.add(projectFormLayout);
        rootFormLayout.add(createProjectButton);
        horizontalLayout.add(rootFormLayout);


        var projectDataBinder = new BeanValidationBinder<>(Project.class);
        projectDataBinder.forField(nameTextField).bind("name");
        projectDataBinder.forField(projectStartDate).bind("startDate");
        projectDataBinder.forField(projectExpectedStartDate).bind("expectedStartDate");
        projectDataBinder.forField(projectEndDate).bind("endDate");
        projectDataBinder.forField(projectExpectedEndDate).bind("expectedEndDate");
        projectDataBinder.forField(cbProducts).bind("product");
        projectDataBinder.setBean(new Project());

        CallbackDataProvider<Project, Void> projectDataProvider = DataProvider
                .fromCallbacks(query -> projectService.getProjects(query.getOffset(), query.getLimit()).stream(),
                        query -> projectService.getProjectCount());

        projectGrid.setDataProvider(projectDataProvider);

        createProjectButton.addClickListener(buttonClickEvent -> {
            var bean = projectDataBinder.getBean();
            projectService.createProduct(bean);
            projectDataBinder.setBean(new Project());
            Notification.show("Product is created");
            projectDataProvider.refreshAll();
        });








    }

}
