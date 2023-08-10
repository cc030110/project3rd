package com.spring.project3rd.domain.boardCommunity;


import com.spring.project3rd.util.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="board_community")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardCommunity extends Timestamp{

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int boardNo;

    @Column(nullable=false)
    private String id;

    @Column
    private String platform;

    @Column(length=100,nullable=false)
    private String title;

    @Column(length=4000)
    private String contents;

    @Column(nullable=false)
    private int participantsNum;

    @Column(nullable=true)
    private java.sql.Timestamp deadline;

    public BoardCommunity(BoardCommunityRequestDto bcDto) {
        super();
//        this.boardNo=bcDto.getBoardNo();
        this.id = bcDto.getId();
        this.platform = bcDto.getPlatform();
        this.title = bcDto.getTitle();
        this.contents = bcDto.getContents();
        this.participantsNum = bcDto.getParticipantsNum();
        this.deadline = bcDto.getDeadline();
    }
}
