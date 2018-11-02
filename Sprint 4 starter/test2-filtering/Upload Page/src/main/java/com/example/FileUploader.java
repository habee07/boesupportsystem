package com.example;

import com.vaadin.server.FileResource;
import com.vaadin.server.SystemError;
import com.vaadin.ui.*;

import javax.servlet.ServletContext;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by Laila on 29/10/2018.
 */
public class FileUploader extends VerticalLayout implements Upload.Receiver, Upload.SucceededListener, Upload.FailedListener {



    public File tempFile; //tempFile is the uploaded file




    @Override
    public OutputStream receiveUpload(String s, String s1) {

        try {
            tempFile = File.createTempFile("temp",".csv");

            //System.out.print(tempFile.getPath());
            return new FileOutputStream(tempFile);
        }

        catch(IOException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent succeededEvent) {

        Notification.show("Upload Successful").setDelayMsec(5000);

    }


    @Override
    public void uploadFailed(Upload.FailedEvent failedEvent) {

        Notification.show("Upload Failed, Try Again.").setDelayMsec(5000);

    }


}
