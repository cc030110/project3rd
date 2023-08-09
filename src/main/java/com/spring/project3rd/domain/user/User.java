package com.spring.project3rd.domain.user;

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
public class User {

    @Id
    private String id;
    private String password;
    @Column
    private String name;
    private String gender;
    private int age;
    private byte[] profile_img;
    @Column
    private String email;
    private short is_active;


}
