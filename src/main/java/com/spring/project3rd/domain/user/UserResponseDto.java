package com.spring.project3rd.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String id;
    private String password;
    private String name;
    private String email;
    private String gender;
    private Short age;
    private String profileImg;
    private String liveCountry;
    private String liveCity;
    private Short warningCount;
    private Short isActive;

    public UserResponseDto (User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.age = user.getAge();
        this.profileImg = user.getProfileImg();
        this.liveCountry = user.getLiveCountry();
        this.liveCity = user.getLiveCity();
    }

}
