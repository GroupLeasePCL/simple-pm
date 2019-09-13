package th.co.grouplease.simple.pm.ui.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import th.co.grouplease.simple.pm.ui.component.PMMenuBar;
import th.co.grouplease.simple.pm.ui.model.Project;
import th.co.grouplease.simple.pm.ui.service.ProjectService;


@Route("project")
public class ProjectView extends VerticalLayout {
    public ProjectView(@Autowired ProjectService projectService) {
        add(new PMMenuBar());
        add(new H2("Project"));


        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidthFull();
        add(horizontalLayout);

        var projectGrid = new Grid<>(Project.class);
        projectGrid.setWidthFull();
        projectGrid.setColumns("projectId", "name", "startDate", "expectedStartDate", "endDate", "expectedEndDate", "status");

        horizontalLayout.add(projectGrid);

        var rootFormLayout = new VerticalLayout();
        rootFormLayout.setWidthFull();
        horizontalLayout.add(rootFormLayout);

        FormLayout projectFormLayout = new FormLayout();
        projectFormLayout.setWidthFull();
        var nameTextField = new TextField("Name");
        var projectStartDate = new DatePicker("Project start date");
        var projectEndDate = new DatePicker("Project end date");
        var projectExpectedStartDate = new DatePicker("Project expected start date");
        var projectExpectedEndDate = new DatePicker("Project expected end date");
        var projectStatus = new TextField("Project status");

        var createProjectButton = new Button("Create");

        projectFormLayout.add(nameTextField);
        projectFormLayout.add(projectStartDate);
        projectFormLayout.add(projectEndDate);
        projectFormLayout.add(projectExpectedStartDate);
        projectFormLayout.add(projectExpectedEndDate);

        rootFormLayout.add(projectFormLayout);
        rootFormLayout.add(createProjectButton);
        horizontalLayout.add(rootFormLayout);

        /*
        var projectDataBinder = new BeanValidationBinder<>(Project.class);
        projectDataBinder.setBean(new Project());

        CallbackDataProvider<Project, Void> projectDataProvider = DataProvider
                .fromCallbacks(query -> projectService.getProjects(query.getOffset(), query.getLimit()).stream(),
                        query -> projectService.getProjectCount());

        projectGrid.setDataProvider(projectDataProvider);



         */


    }

}
