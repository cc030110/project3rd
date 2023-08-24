package com.spring.project3rd.domain.participant;

import com.spring.project3rd.domain.platform.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ParticipantRepository extends JpaRepository<Participant,String> {
    // user id를 이용하여 participant id 조회
    Participant findByBoardNoAndParticipantId(int boardNo, String participantId);



}