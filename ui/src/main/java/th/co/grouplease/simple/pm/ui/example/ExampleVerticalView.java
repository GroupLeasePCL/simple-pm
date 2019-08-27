package th.co.grouplease.simple.pm.ui.example;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("vertical")
public class ExampleVerticalView extends VerticalLayout {

    public ExampleVerticalView(){
        var horizontalLayout1 = new HorizontalLayout();
        horizontalLayout1.add(new H1("Item1-1"));
        horizontalLayout1.add(new H1("Item1-2"));
        var horizontalLayout2 = new HorizontalLayout();
        horizontalLayout2.add(new H1("Item2-1"));
        horizontalLayout2.add(new H1("Item2-2"));

        add(horizontalLayout1);
        add(horizontalLayout2);
    }
}
