package com.spring.project3rd.domain.boardFree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardFreeRequestDto {
    private int boardNo;
    private String id;
    private String title;
    private String contents;
    private short isModified;
}
