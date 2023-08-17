package com.spring.project3rd.domain.boardCommunityComment;

import com.spring.project3rd.util.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class BoardCommunityComment extends Timestamp {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_incerement
    private int commentNo;

    @Column
    private int boardNo;

    @Column
    private String id;

    @Column(nullable=false)
    private String contents;

    @Column
    private short isModified;

    public BoardCommunityComment(BoardCommunityCommentRequestDto bccDto){
        super();
        this.boardNo=bccDto.getBoardNo();
        this.id = bccDto.getId();
        this.contents = bccDto.getContents();
    }

    public void update(BoardCommunityCommentRequestDto bccDto){
        this.contents=bccDto.getContents();
        this.isModified=bccDto.getIsModified();
    }
}
