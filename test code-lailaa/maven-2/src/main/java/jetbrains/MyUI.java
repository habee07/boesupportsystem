package jetbrains;

import javax.servlet.annotation.WebServlet;

import java.util.Iterator;
import java.util.Random;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Grid;

import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {


    private Grid attr;
    private Grid savedAttr;
    private Random rnd;


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        HorizontalLayout topHorLay = new HorizontalLayout();
        topHorLay.setSizeFull();

        topHorLay.setSpacing(true);
        VerticalLayout butLay = new VerticalLayout();
        butLay.setSizeFull();
        butLay.setSpacing(true);
        Label butLabel = new Label("Is this OK?");
        butLabel.setStyleName("h2");
    }

        @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
        @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
        public static class MyUIServlet extends VaadinServlet {
        }
    }

