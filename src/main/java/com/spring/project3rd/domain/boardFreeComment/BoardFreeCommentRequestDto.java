package com.spring.project3rd.domain.boardFreeComment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardFreeCommentRequestDto {
    private int comment_no;
    private int board_no;
    private String id;
    private String contents;
    private short isModified;

}
