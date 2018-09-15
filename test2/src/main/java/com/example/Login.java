package com.example;

import com.vaadin.navigator.View;
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
    public Login(){
        tfID = new TextField("zID");
        tfPassword = new PasswordField("Password");
        HorizontalLayout myLayout = new HorizontalLayout();

        btnLogin = new Button("Login");
        btnLogin.addStyleName("friendly");

        btnSignUp = new Button("Sign Up");
        btnSignUp.addStyleName("primary");

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
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event){
        throw new UnsupportedOperationException("Not supported");
    }

}
