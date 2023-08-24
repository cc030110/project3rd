package com.spring.project3rd.domain.user;

import com.spring.project3rd.util.Timestamp;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="user")
@Entity
@ToString
public class User extends Timestamp {

    @Id
    private String id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String gender;
    private int birth;
    private String profileImg;
    private String email;
    private String liveCountry;
    private String liveCity;
    private Short warningCount;
    private Short isActive;
    private String intro;


    public User(UserRequestDto userRequestDto){
        this.id = userRequestDto.getId();
        this.password = userRequestDto.getPassword();
        this.name = userRequestDto.getName();
        this.gender = userRequestDto.getGender();
        this.birth = userRequestDto.getBirth();
        this.email = userRequestDto.getEmail();
        this.liveCountry = userRequestDto.getLiveCountry();
        this.liveCity = userRequestDto.getLiveCity();
        this.profileImg = userRequestDto.getProfileImg();
        this.isActive = 1;
        this.warningCount = 0;
        this.intro = userRequestDto.getIntro();
    }

    public void update(UserRequestDto userRequestDto, String url){
        this.password = userRequestDto.getPassword();
        this.profileImg = url;
        this.liveCountry = userRequestDto.getLiveCountry();
        this.liveCity = userRequestDto.getLiveCity();
        this.intro = userRequestDto.getIntro();
    }
}
