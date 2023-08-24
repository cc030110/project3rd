package com.spring.project3rd.service;

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
    @Transactional
    public void updateBoard(int no, BoardFreeRequestDto boardDto){
        BoardFree board = boardFreeRepository.findById(no).orElseThrow(()->new IllegalArgumentException("해당 게시글 없음"));
        board.update(boardDto);
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
