package com.spring.project3rd.domain.language;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="use_language")
@Setter
@ToString
public class UseLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usedNo;
    private String id;
    private String languageName;
}
