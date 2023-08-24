package com.spring.project3rd.domain.participant;

import com.spring.project3rd.domain.platform.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ParticipantRepository extends JpaRepository<Participant,String> {

    // boardNo와 id를 입력받아 해당하는 신청 내역 가져오기
    Participant findByBoardNoAndParticipantId(int boardNo, String participantId);

    // boardNo를 받아 현재 수락된 내역들 가져오기
    @Query(value = "SELECT p FROM Participant p WHERE p.boardNo = ?1 AND p.isAccept = 1")
    List<Participant> getParticipantsAcceptByBoardNo(int boardNo);
}
