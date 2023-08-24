package com.spring.project3rd.domain.participant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantRequestDto {
    private int participantNo;
    private int boardNo;
    private String participantId;
    private short isAccept;

    public ParticipantRequestDto(int boardNo, String participantId){
        this.boardNo=boardNo;
        this.participantId=participantId;
    }
}
