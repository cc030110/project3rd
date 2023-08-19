package com.spring.project3rd.domain.language;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="use_language")
@ToString
public class UseLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int useNo;
    private String id;
    private String languageCode;

    public UseLanguage(String id, String code){
        this.id=id;
        this.languageCode=code;
    }
}
