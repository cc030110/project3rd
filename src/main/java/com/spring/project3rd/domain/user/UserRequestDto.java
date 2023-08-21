package com.spring.project3rd.domain.user;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.security.Timestamp;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequestDto {
    private String id;
    private String password;
    private String name;
    private String email;
    private String gender;
    private int birth;
    private String profileImg;
    private String liveCountry;
    private String liveCity;
    private Short warningCount;
    private Short isActive;
    // 언어 정보도 같이 받기
    private List<String> useLang;
    private List<String> needLang;
}