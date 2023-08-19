package com.spring.project3rd.domain.language;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="need_language")
@ToString
public class NeedLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int needNo;
    private String id;
    private String languageCode;

    public NeedLanguage(String id, String code){
        this.id=id;
        this.languageCode=code;
    }
}
