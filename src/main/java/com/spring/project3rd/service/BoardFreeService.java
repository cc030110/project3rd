package com.spring.project3rd.service;

import com.spring.project3rd.domain.boardCommunity.BoardCommunity;
import com.spring.project3rd.domain.boardCommunity.BoardCommunityRequestDto;
import com.spring.project3rd.domain.boardFree.BoardFree;
import com.spring.project3rd.domain.boardFree.BoardFreeRepository;
import com.spring.project3rd.domain.boardFree.BoardFreeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardFreeService {
    private final BoardFreeRepository boardFreeRepository;

    // 삭제
    @Transactional
    public void deleteBoard(int no){
        boardFreeRepository.deleteById(no);
    }

    // 수정
    /*@Transactional
    public void updateBoard(int no, BoardFreeRequestDto boardDto){
        BoardFree board = boardFreeRepository.findById(no).orElseThrow(()->new IllegalArgumentException("해당 게시글 없음"));
        board.update(boardDto);
    }*/
    @Transactional // 이 어노테이션은 public 클래스에만 적용
    public void updateBoardByBoardNo(int boardNo, String name, BoardFreeRequestDto bfDto){
        BoardFree bf=boardFreeRepository.getBoardFreeByBoardNoAndName(boardNo,name);
        bf.update(bfDto);
    }

    // 조회수 1 증가
    @Transactional
    public void addViews(BoardFree board){
        board.viewPlus();
    }

    // 유저가 작성한 게시글 목록
    public List<BoardFree> getBoardListById(String id){
        List<BoardFree> list = new ArrayList<>();
        list = boardFreeRepository.findById(id);
        return list;
    }

}
