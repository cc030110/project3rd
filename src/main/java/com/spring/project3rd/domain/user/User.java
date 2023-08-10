package com.spring.project3rd.domain.user;

import com.spring.project3rd.util.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="user")
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
        this.liveCity=userRequestDto.getLiveCity();
        this.isActive = 1;
        this.warningCount = 0;
        if(userRequestDto.getGender()==null){
            this.gender="남";
        }else{
            this.gender=userRequestDto.getGender();
        }

    }

    public void update(UserRequestDto userRequestDto){
        this.password = userRequestDto.getPassword();
        this.gender = userRequestDto.getGender();
        this.age = userRequestDto.getAge();
        this.profileImg = userRequestDto.getProfileImg();
        this.liveCountry = userRequestDto.getLiveCountry();
        this.liveCity = userRequestDto.getLiveCity();
        this.warningCount = userRequestDto.getWarningCount();
        this.isActive = userRequestDto.getIsActive();
    }



}
