package th.co.grouplease.simple.pm.ui.component;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.router.RouterLink;
import th.co.grouplease.simple.pm.ui.main.ProductView;
import th.co.grouplease.simple.pm.ui.main.ProjectView;

public class PMMenuBar extends MenuBar {
    public PMMenuBar(){
        addItem(new RouterLink("Product", ProductView.class));
        addItem(new RouterLink("Project", ProjectView.class));
        MenuItem m1Resource = addItem("Resource");
        SubMenu m2Resource = m1Resource.getSubMenu();
        MenuItem m2ResourceTeam = m2Resource.addItem("Resource Team");
        MenuItem m2ResourceAll = m2Resource.addItem("Resource All");


    }
}
