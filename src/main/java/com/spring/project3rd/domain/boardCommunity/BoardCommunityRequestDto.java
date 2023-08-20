package com.spring.project3rd.domain.boardCommunity;

import com.spring.project3rd.util.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class BoardCommunityRequestDto{
    private int boardNo;
    private String id;
    private String platformName;
    private String title;
    private String contents;
    private int participantsNum;
    private Date deadline;
    private short isModified;
    private int views;
}
