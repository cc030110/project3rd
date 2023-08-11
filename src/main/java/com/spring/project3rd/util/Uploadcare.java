package com.spring.project3rd.util;

import com.uploadcare.api.Client;
import com.uploadcare.api.File;
import com.uploadcare.upload.FileUploader;
import com.uploadcare.upload.UploadFailureException;
import com.uploadcare.upload.Uploader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Uploadcare {

    @Value("${uploadcare.secret.keys.public-key}")
    private String publicKey;

    @Value("${uploadcare.secret.keys.secret-key}")
    private String secretKey;

    private Uploadcare() {}
    private static Uploadcare instance = new Uploadcare();
    public static Uploadcare getInstance() {
        return instance;
    }

    public String getUploadFileUrl(String LOCAL_FILE_NAME) throws UploadFailureException {
        System.out.println("publicKey : " + publicKey);
        System.out.println("secretKey : " + secretKey);

        Client client = new Client(publicKey, secretKey);

        java.io.File localFile = new java.io.File(Optional.ofNullable(getClass().getClassLoader()
                        .getResource(LOCAL_FILE_NAME)).map(java.net.URL::getFile)
                .orElseThrow(RuntimeException::new));
        Uploader uploader = new FileUploader(client, localFile);
        File uploadedFile = uploader.upload();
        String uploadedFileId = uploadedFile.getFileId();

        return String.format("https://ucarecdn.com/%s/", uploadedFileId);
    }

}