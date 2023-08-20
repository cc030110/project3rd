package com.spring.project3rd.domain.boardCommunity;


import com.spring.project3rd.domain.platform.Platform;
import com.spring.project3rd.util.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="board_community")
@Entity
public class BoardCommunity extends Timestamp{

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int boardNo;

    @Column(nullable=false)
    private String id;

    @Column
    private String platformName;

    @Column(length=100,nullable=false)
    private String title;

    @Column(length=4000,nullable = false)
    private String contents;

    @Column(nullable=false)
    private int participantsNum;

    @Column(nullable=true)
    private Date deadline;

    @Column
    private short isModified;

    @Column(nullable=false)
    private int views;


    public BoardCommunity(BoardCommunityRequestDto bcDto) {
        super();
        this.id = bcDto.getId();
        this.platformName = bcDto.getPlatformName();
        this.title = bcDto.getTitle();
        this.contents = bcDto.getContents();
        this.participantsNum = bcDto.getParticipantsNum();
        this.deadline=bcDto.getDeadline();
        this.isModified=bcDto.getIsModified();
        this.views=0;
    }

    // 조회수 증가
    public void viewPlus(){
        this.views+=1;
    }

    public void update(BoardCommunityRequestDto bcDto){
        this.platformName= bcDto.getPlatformName();
        this.title=bcDto.getTitle();
        this.contents=bcDto.getContents();
        this.participantsNum = bcDto.getParticipantsNum();
        this.deadline=bcDto.getDeadline();
        this.isModified=bcDto.getIsModified();
    }
}
