package com.spring.project3rd.domain.participant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="participant")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int participantNo;
    private int boardNo;
    private String participantId;
    private int isAccept;

    public Participant(int boardNo,String id){
        this.boardNo = boardNo;
        this.participantId = id;
        this.isAccept = 0;
    }

    public void update(ParticipantRequestDto dto){
        this.isAccept=dto.getIsAccept();
    }
}
