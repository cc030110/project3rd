package com.spring.project3rd.domain.boardFree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardFreeResponseDto {
    private int boardNo;
    private String id;
    private String title;
    private String contents;
    private short isModified;

    public BoardFreeResponseDto(String id, String title, String contents){
        this.id=id;
        this.title=title;
        this.contents=contents;
        this.isModified=0;
    }
}
