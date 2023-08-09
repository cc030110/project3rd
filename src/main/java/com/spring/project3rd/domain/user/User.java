package com.spring.project3rd.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.IOException;
import java.security.Timestamp;

@NoArgsConstructor
@Getter
@Table(name="user")
@Entity
@ToString
public class User {

    @Id
    private String id;
    private String password;
    @Column
    private String name;
    private String gender;
    private int age;
    @Column(name = "profile_img")
    private byte[] profileImg;
    private String email;
    @Column(name = "live_country")
    private String liveCountry;
    @Column(name = "live_city")
    private String liveCity;
    @Column(name = "warning_count")
    private short warningCount;
    @Column(name = "is_active")
    private short isActive;
    @Column(name = "created_at")
    private Timestamp createdAt;


    public User(UserRequestDto userRequestDto){
        this.id = userRequestDto.getId();
        this.password = userRequestDto.getPassword();
        this.name = userRequestDto.getName();
        this.email = userRequestDto.getEmail();
        this.gender = userRequestDto.getGender();
        this.createdAt = userRequestDto.getCreateAt();
    }
}
