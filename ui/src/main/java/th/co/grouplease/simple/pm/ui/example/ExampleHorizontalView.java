package th.co.grouplease.simple.pm.ui.example;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("horitontal")
public class ExampleHorizontalView extends HorizontalLayout {

    public ExampleHorizontalView(){
        add(new H1("Item1"));
        add(new H1("Item2"));
    }
}
