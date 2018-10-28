package com.example;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.sql.SQLException;

/**
 * Created by habee on 2018/09/14.
 */
public class Login extends VerticalLayout implements View {

    TextField tfID;
    PasswordField tfPassword;
    Button btnSignUp;
    Button btnLogin;
    Users SignIn;

    public Login(){

        tfID = new TextField("Username:");
        tfPassword = new PasswordField("Password:");
        HorizontalLayout myLayout = new HorizontalLayout();

        btnLogin = new Button("Login");
        btnLogin.addStyleName("friendly");
        btnLogin.setIcon(VaadinIcons.SIGN_IN);

        btnLogin.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                /* if(signup.LoginCheck(tfID.getValue(), tfPassword.getValue())){
                    getUI().getNavigator().navigateTo("dashboard");
                    String user = tfID.getValue();
                    tfPassword.setValue("");
                    tfID.setValue("");
                    Notification.show("Welcome " + user + " :)");

                }
                else{
                    Notification.show("Incorrect Login Details!", Notification.Type.ERROR_MESSAGE);
                }*/

                //Database Code For user login

                try {

                    SignIn = new Users(tfPassword.getValue(), tfID.getValue());

                    if(SignIn.Login()){

                        getUI().getNavigator().navigateTo("dashboard");

                        tfPassword.setValue("");
                        tfID.setValue("");

                        Notification.show("Sign in Successful!").setDelayMsec(2000);

                    }
                    else{

                        Notification.show("Incorrect Login Details!", Notification.Type.ERROR_MESSAGE);

                    }

                } catch (ClassNotFoundException e) {
                    Notification.show("Error Occurred, Please Try Again!", Notification.Type.ERROR_MESSAGE);
                    e.printStackTrace();
                } catch (SQLException e) {
                    Notification.show("Error Occurred, Please Try Again!", Notification.Type.ERROR_MESSAGE);
                    e.printStackTrace();
                }


            }
        });

        btnSignUp = new Button("Sign Up");
        btnSignUp.addStyleName("primary");
        btnSignUp.setIcon(VaadinIcons.USER);

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
