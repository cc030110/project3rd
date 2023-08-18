package com.spring.project3rd.domain.boardFreeComment;


import com.spring.project3rd.util.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board_free_comment")
public class BoardFreeComment extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_no;
    private int board_no;
    private String id;
    private String contents;
    private short isModified;

}
