package com.example;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

import java.sql.SQLException;

/**
 * Created by habee on 2018/09/14.
 */
public class SignUpForm extends VerticalLayout implements View {

    Label lblTitle;
    Label lblHeader;
    TextField tfID;
    TextField tfName;
    TextField tfEmail;
    TextArea taBio;
    PasswordField tfPassword;
    TextField tfDiscipline;
    Button btnConfirm;
    Button btnCancel;
    RadioButtonGroup opGender;
    Users User;

    public SignUpForm(){
        setSpacing(true);
        setMargin(true);

        lblTitle = new Label("Sign Up Form");
        lblTitle.addStyleName("h1");
        addComponent(lblTitle);

        FormLayout formLayout= new FormLayout();
        formLayout.setMargin(false);
        formLayout.addStyleName("light");
        addComponent(formLayout);

        lblHeader = new Label("Personal Information");
        lblHeader.addStyleName("h2");
        lblHeader.addStyleName("coloured");
        lblHeader.setIcon(VaadinIcons.USER_CARD);
        formLayout.addComponent(lblHeader);

        tfID = new TextField("Username:");
        tfID.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfID);

        tfEmail = new TextField("Email Address:");
        tfEmail.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfEmail);

        tfName = new TextField("Full Name:");
        tfName.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfName);

        tfPassword = new PasswordField("Password:");
        tfPassword.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfPassword);

        tfDiscipline = new TextField("Discipline:");
        tfDiscipline.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfDiscipline);


        taBio = new TextArea("Bio:");
        formLayout.addComponent(taBio);

        opGender = new RadioButtonGroup("Gender");
        opGender.setItems("Male", "Female", "Other");
        opGender.setRequiredIndicatorVisible(true);
        opGender.addStyleName("horizontal");
        formLayout.addComponent(opGender);


        btnConfirm = new Button("Confirm");
        btnCancel =  new Button("Cancel");
        btnConfirm.setIcon(VaadinIcons.USER_CHECK);
        btnConfirm.addStyleName("primary");
        btnCancel.addStyleName("danger");
        btnCancel.setIcon(VaadinIcons.CLOSE_CIRCLE);
        btnConfirm.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                String Bio = taBio.getValue();

                if(Bio.equals("")){

                    Bio = "-1";

                }

                User = new Users(tfID.getValue(), opGender.getValue().toString(), tfDiscipline.getValue(), tfPassword.getValue(), Bio, tfEmail.getValue(), tfName.getValue());

                Boolean Auth = false;
                try {
                    Auth = User.MakeUser();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                tfID.setValue("");
                tfDiscipline.setValue("");
                tfPassword.setValue("");
                tfEmail.setValue("");
                tfName.setValue("");
                taBio.setValue("");

                if (Auth){

                    Notification.show("You have signed up successfully!").setDelayMsec(2000);
                    getUI().getNavigator().navigateTo("login");

                }
                else{

                    Notification.show("Username already in use, Please try again!",Notification.Type.ERROR_MESSAGE);
                    getUI().getNavigator().navigateTo("signup");

                }

            }
        });

        btnCancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().getNavigator().navigateTo("login");
            }
        });

        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, false, true, false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        formLayout.addComponent(footer);
        footer.addComponent(btnConfirm);
        footer.addComponent(btnCancel);




    }
}
