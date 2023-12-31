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
    private int birth;
    private String profileImg;
    private String liveCountry;
    private String liveCity;
    private Short warningCount;
    private Short isActive;
    private String intro;

    public UserResponseDto (User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.birth = user.getBirth();
        this.profileImg = user.getProfileImg();
        this.liveCountry = user.getLiveCountry();
        this.liveCity = user.getLiveCity();
        this.intro = user.getIntro();
    }

}
