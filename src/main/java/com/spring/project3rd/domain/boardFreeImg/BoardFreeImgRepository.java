package com.spring.project3rd.domain.boardFreeImg;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardFreeImgRepository extends JpaRepository<BoardFreeImg,Integer> {

    public List<BoardFreeImg> findByBoardNo(int boardNo);
}