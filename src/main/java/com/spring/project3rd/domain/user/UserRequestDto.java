package com.spring.project3rd.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.security.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String id;
    private String password;
    private String name;
    private String gender;
    private int age;
    private byte[] profileImg;
    private String email;
    private String liveCountry;
    private String liveCity;
    private short warningCount;
    private short isActive;
}