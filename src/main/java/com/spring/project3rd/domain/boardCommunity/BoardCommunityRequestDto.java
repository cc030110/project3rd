package com.spring.project3rd.domain.boardCommunity;

import com.spring.project3rd.util.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardCommunityRequestDto {
    private int boardNo;
    private String id;
    private String platform;
    private String title;
    private String contents;
    private int participantsNum;
    private java.sql.Timestamp deadline;
    private short isModified;
}
