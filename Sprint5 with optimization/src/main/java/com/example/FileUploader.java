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
import java.sql.Connection;
import java.util.List;

/**
 * Created by Laila on 29/10/2018.
 */
public class FileUploader implements Upload.Receiver, Upload.SucceededListener, Upload.FailedListener {


    private File tempFile; //tempFile is the uploaded file
    String Path;

    @Override
    public OutputStream receiveUpload(String s, String s1) {

        try {
            tempFile = File.createTempFile("temp",".csv");
            Path = tempFile.getPath();

            //System.out.print(tempFile.getPath());
            return new FileOutputStream(tempFile);
        } catch (IOException e) {
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

        Notification.show("Upload Failed, Try Again.", Notification.Type.ERROR_MESSAGE).setDelayMsec(5000);

    }


    public File getTempFile() {
        return tempFile;
    }


}