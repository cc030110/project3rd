package com.spring.project3rd.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.security.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="user")
@Entity
@ToString
public class User {

    @Id
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


    public User(UserRequestDto userRequestDto){
        this.id = userRequestDto.getId();
        this.password = userRequestDto.getPassword();
        this.name = userRequestDto.getName();
        this.email = userRequestDto.getEmail();
        this.gender = userRequestDto.getGender();
        this.liveCountry = userRequestDto.getLiveCountry();
        this.liveCity=userRequestDto.getLiveCity();
    }
}
