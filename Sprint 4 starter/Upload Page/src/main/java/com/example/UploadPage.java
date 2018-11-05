package com.example;


import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.ui.*;

import java.io.File;
import java.io.OutputStream;


/**
 * Created by Laila on 29/10/2018.
 */
public class UploadPage extends VerticalLayout implements View {

    VerticalLayout uploadlayout = new VerticalLayout();
    Label uploadHistory;
    Label uploadCourses;
    Label sz;
    private Upload uploadC;
    private Upload uploadH;
    HorizontalLayout UpperSection = new HorizontalLayout();
    Label lblHeader;
    Button goBack;


  public UploadPage() {

      lblHeader = new Label("");
      lblHeader.setSizeUndefined();

      goBack = new Button("Dashboard");
      goBack.addStyleName("small");
      goBack.addStyleName("friendly");
      goBack.setIcon(VaadinIcons.ARROW_BACKWARD);
      goBack.setSizeUndefined();
      goBack.addClickListener(new Button.ClickListener() {
          @Override
          public void buttonClick(Button.ClickEvent clickEvent) {

              getUI().getNavigator().navigateTo("dashboard");
          }
      });

      UpperSection.addComponents(lblHeader, goBack);
      UpperSection.setComponentAlignment(goBack, Alignment.TOP_LEFT);
      UpperSection.addStyleName("borderBottom");
      addComponent(UpperSection);



       FileUploader receiverC = new FileUploader();
       FileUploader receiverH = new FileUploader();



       uploadC = new Upload("Choose File", receiverC);
       uploadC.setButtonCaption("Browse...");
       uploadC.addSucceededListener(receiverC);
       uploadC.addFailedListener(receiverC);
       uploadH = new Upload("Choose File", receiverH);
       uploadH.setButtonCaption("Browse...");
       uploadH.addSucceededListener(receiverH);
       uploadH.addFailedListener(receiverH);



       uploadC.setImmediateMode(true);
       uploadC.setVisible(true);

      uploadH.setImmediateMode(true);
      uploadH.setVisible(true);

      uploadHistory = new Label("Upload Academic History .csv File");
      uploadHistory.addStyleName("colored");
      uploadHistory.addStyleName("h2");
      uploadCourses = new Label("Upload Courses .csv File");
      uploadCourses.addStyleName("colored");
      uploadCourses.addStyleName("h2");
      sz = new Label("");
      sz.setWidth( null );
      sz.setHeight ( "70px" );

     // File uploadedCFile = receiverC.getTempFile();
     // File uploadedHFile = receiverH.getTempFile();


       uploadlayout.addComponents(uploadHistory,uploadH,sz,uploadCourses,uploadC);
       addComponent(uploadlayout);


   }
}
