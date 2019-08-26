package th.co.grouplease.simple.pm.ui.example;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("example")
public class ExampleView extends VerticalLayout {

    public ExampleView(){
        add(new H1("I'm an example view"));
    }
}
