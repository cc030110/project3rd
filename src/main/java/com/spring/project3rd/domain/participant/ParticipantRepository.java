package com.spring.project3rd.domain.participant;

import com.spring.project3rd.domain.platform.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ParticipantRepository extends JpaRepository<Participant,String> {

    // boardNo와 id를 입력받아 해당하는 신청 내역 가져오기
    Participant findByBoardNoAndParticipantId(int boardNo, String participantId);

    // boardNo과 수락 형태(0신청/1수락/2거절)에 맞는 리스트 가져오기
    List<Participant> findByBoardNoAndIsAccept(int boardNo, int isAccept);
}
