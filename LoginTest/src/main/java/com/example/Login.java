package com.example;

/**
 * Created by habee on 2018/09/14.
 */import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

/**
 * Created by habee on 2018/09/14.
 */
public class Login extends VerticalLayout implements View {
    TextField tfID;
    PasswordField tfPassword;
    Button btnSignUp;
    Button btnLogin;
    Signup signup = new Signup();
    public Login(){
        tfID = new TextField("Username:");
        tfPassword = new PasswordField("Password:");
        HorizontalLayout myLayout = new HorizontalLayout();

        btnLogin = new Button("Login");
        btnLogin.addStyleName("friendly");

        btnLogin.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(signup.LoginCheck(tfID.getValue(), tfPassword.getValue())){
                    getUI().getNavigator().navigateTo("dashboard");
                    String user = tfID.getValue();
                    tfPassword.setValue("");
                    tfID.setValue("");
                    Notification.show("Welcome " + user + " :)");

                }
                else{
                    Notification.show("Incorrect Login Details!", Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        btnSignUp = new Button("Sign Up");
        btnSignUp.addStyleName("primary");

        btnSignUp.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().getNavigator().navigateTo("signup");
                tfPassword.setValue("");
                tfID.setValue("");

            }
        });

        myLayout.addComponent(btnLogin);
        myLayout.addComponent(btnSignUp);
        myLayout.setSpacing(true);

        FormLayout formLayout = new FormLayout(tfID,tfPassword, myLayout);
        formLayout.setMargin(true);

        Panel loginPanel = new Panel("Board of Examinations System", formLayout);
        loginPanel.setWidth("450");
        loginPanel.setHeight("250");

        addComponent(loginPanel);
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        setHeight("100%");
    }

}
