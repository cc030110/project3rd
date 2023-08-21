package com.spring.project3rd.util;

import com.uploadcare.api.Client;
import com.uploadcare.api.File;
import com.uploadcare.upload.FileUploader;
import com.uploadcare.upload.UploadFailureException;
import com.uploadcare.upload.Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Uploadcare {
    private final Environment env;
    public String getUploadFileUrl(String LOCAL_FILE_NAME) throws UploadFailureException {

        String publicKey = env.getProperty("uploadcare.secret.keys.public-key");
        String secretKey = env.getProperty("uploadcare.secret.keys.secret-key");

        Client client = new Client(publicKey, secretKey);

        java.io.File localFile = new java.io.File(LOCAL_FILE_NAME);

        Uploader uploader = new FileUploader(client, localFile);
        File uploadedFile = uploader.upload();
        String uploadedFileId = uploadedFile.getFileId();

        return String.format("https://ucarecdn.com/%s/", uploadedFileId);
    }

}