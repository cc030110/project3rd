package com.spring.project3rd.service;

import com.spring.project3rd.domain.boardCommunity.BoardCommunityRepository;
import com.spring.project3rd.domain.participant.Participant;
import com.spring.project3rd.domain.participant.ParticipantRepository;
import com.spring.project3rd.domain.participant.ParticipantRequestDto;
import com.spring.project3rd.domain.user.User;
import com.spring.project3rd.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    // boardNo의 모든 신청기록 받기
    public List<Participant> getListAllByBoardNo(int boardNo){
        return participantRepository.findAllByBoardNo(boardNo);
    }

    // 유저의 모든 게시판 신청기록 받기
    public List<Participant> getListAllByUserId(String id){
        return participantRepository.findAllByParticipantId(id);

    }

    // boardNo와 수락 여부를 받아 해당 리스트 받기
    public List<Participant> getListByBoardNoAndAccept(int boardNo,int isAccept){
        return participantRepository.findByBoardNoAndIsAccept(boardNo, isAccept);
    }

    // boardNo와 id를 입력받아 일치하는 신청기록 받기
    public Participant getParticipant(int boardNo, String id){
        return participantRepository.findByBoardNoAndParticipantId(boardNo,id);
    }

    // 업데이트
    @Transactional
    public void acceptParticipant(ParticipantRequestDto dto){
        Participant participant = participantRepository.findByBoardNoAndParticipantId(dto.getBoardNo(), dto.getParticipantId());
        participant.update(dto);
    }
}
