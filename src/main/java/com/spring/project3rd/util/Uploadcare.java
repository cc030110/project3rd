package com.spring.project3rd.util;

import com.uploadcare.api.Client;
import com.uploadcare.api.File;
import com.uploadcare.upload.FileUploader;
import com.uploadcare.upload.UploadFailureException;
import com.uploadcare.upload.Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class Uploadcare {
    private final Environment env;
    public String getUploadFileUrl(String LOCAL_FILE_NAME) throws UploadFailureException {

        String publicKey = env.getProperty("uploadcare.secret.keys.public-key");
        String secretKey = env.getProperty("uploadcare.secret.keys.secret-key");

//        System.out.println("publicKey : " + publicKey);
//        System.out.println("secretKey : " + secretKey);

        Client client = new Client(publicKey, secretKey);

//        System.out.println("LOCAL_FILE_NAME : " + LOCAL_FILE_NAME);

        java.io.File localFile = new java.io.File(LOCAL_FILE_NAME);

//        java.io.File localFile = new java.io.File(Optional.ofNullable(getClass().getClassLoader()
//                        .getResource(LOCAL_FILE_NAME)).map(java.net.URL::getFile)
//                .orElseThrow(RuntimeException::new));

        Uploader uploader = new FileUploader(client, localFile);
        File uploadedFile = uploader.upload();
        String uploadedFileId = uploadedFile.getFileId();

        return String.format("https://ucarecdn.com/%s/", uploadedFileId);
    }

}