package com.spring.project3rd.domain.language;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="need_language")
@Setter
@ToString
public class NeedLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int needNo;
    private String id;
    private String languageName;
}
