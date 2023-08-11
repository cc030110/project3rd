package com.spring.project3rd.service;

import com.spring.project3rd.domain.boardCommunity.BoardCommunity;
import com.spring.project3rd.domain.boardCommunity.BoardCommunityRepository;
import com.spring.project3rd.domain.boardCommunity.BoardCommunityRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardCommunityService {

    private final BoardCommunityRepository boardCommunityRepository;

    // Update
    @Transactional // 이 어노테이션은 public 클래스에만 적용
    public void updateBoardByBoardNo(int boardNo, String id, BoardCommunityRequestDto bcDto){
        BoardCommunity bc=boardCommunityRepository.getBoardCommunityByBoardNoAndId(boardNo,id);
        bc.update(bcDto);
    }

    // Delete
    @Transactional
    public void deleteBoardByBoardNo(int boardNo){
        boardCommunityRepository.deleteById(boardNo);
    }
}
