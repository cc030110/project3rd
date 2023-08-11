package com.spring.project3rd.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties("uploadcare.secret")
public class UploadcareApiProperties {

    private boolean enable;
    private Keys keys = new Keys();

    @Setter
    @Getter
    private static class Keys {
        private String publicKey;
        private String secretKey;
    }

}
