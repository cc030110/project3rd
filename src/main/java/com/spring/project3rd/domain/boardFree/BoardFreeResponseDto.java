package com.spring.project3rd.domain.boardFree;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardFreeResponseDto {
    private int boardNo;
    private String id;
    private String title;
    private String contents;
    private short isModified;
    private int views;
}
