package com.spring.project3rd.domain.user;

import com.spring.project3rd.util.Timestamp;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="user")
@Setter
@Entity
@ToString
public class User extends Timestamp {

    @Id
    private String id;
    private String password;
    private String name;
    private String gender;
    private Short age;
    private String profileImg;
    private String email;
    private String liveCountry;
    private String liveCity;
    private Short warningCount;
    private Short isActive;


    public User(UserRequestDto userRequestDto){
        this.id = userRequestDto.getId();
        this.password = userRequestDto.getPassword();
        this.name = userRequestDto.getName();
        this.age = userRequestDto.getAge();
        this.email = userRequestDto.getEmail();
        this.liveCountry = userRequestDto.getLiveCountry();
        this.liveCity = userRequestDto.getLiveCity();
        this.profileImg = userRequestDto.getProfileImg();
        this.isActive = 1;
        this.warningCount = 0;
        this.gender = userRequestDto.getGender();
    }

    public void update(UserRequestDto userRequestDto, String url){
        this.password = userRequestDto.getPassword();
        this.gender = userRequestDto.getGender();
        this.age = userRequestDto.getAge();
        this.profileImg = url;
        this.liveCountry = userRequestDto.getLiveCountry();
        this.liveCity = userRequestDto.getLiveCity();
        this.warningCount = userRequestDto.getWarningCount();
        this.isActive = userRequestDto.getIsActive();
    }
}
