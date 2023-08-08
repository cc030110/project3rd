package com.spring.project3rd.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="users")
@Entity
@ToString
public class User {

    @Id
    private String id;
    private String password;
    private String name;
    private String gender;
    private int age;
    private byte[] profile_img;
    private String email;
    private int country_no;
    private String city;
    private String needs_lang;
    private String mother_lang;
    private int warning;
    private short is_active;


}