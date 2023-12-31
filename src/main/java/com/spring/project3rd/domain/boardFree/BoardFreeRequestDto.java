package com.spring.project3rd.domain.boardFree;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardFreeRequestDto {
    private int boardNo;
    private String id;
    private String name;
    private String title;
    private String contents;
    private short isModified;
    private int views;
}
