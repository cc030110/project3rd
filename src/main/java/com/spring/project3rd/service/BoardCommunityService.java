package com.spring.project3rd.service;

import com.spring.project3rd.domain.boardCommunity.BoardCommunity;
import com.spring.project3rd.domain.boardCommunity.BoardCommunityRepository;
import com.spring.project3rd.domain.boardCommunity.BoardCommunityRequestDto;
import com.spring.project3rd.domain.boardFree.BoardFree;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardCommunityService {

    private final BoardCommunityRepository boardCommunityRepository;

    // 수정
    @Transactional // 이 어노테이션은 public 클래스에만 적용
    public void updateBoardByBoardNo(int boardNo, String name, BoardCommunityRequestDto bcDto){
        BoardCommunity bc=boardCommunityRepository.getBoardCommunityByBoardNoAndName(boardNo,name);
        bc.update(bcDto);
    }

    // 삭제
    @Transactional
    public void deleteBoardByBoardNo(int boardNo){
        boardCommunityRepository.deleteById(boardNo);
    }

    // 조회수 1 증가
    @Transactional
    public void addViews(BoardCommunity board){
        board.viewPlus();
    }

    // 유저가 작성한 게시글 목록
    public List<BoardCommunity> getBoardListById(String id){
        List<BoardCommunity> list = new ArrayList<>();
        list = boardCommunityRepository.findAllById(id);
        return list;
    }

    // 보드 리턴
    public BoardCommunity getBoard(int boardNo){
        return boardCommunityRepository.findById(boardNo).orElse(null);
    }
}
