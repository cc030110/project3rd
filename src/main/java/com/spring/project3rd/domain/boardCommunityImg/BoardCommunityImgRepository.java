package com.spring.project3rd.domain.boardCommunityImg;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommunityImgRepository extends JpaRepository<BoardCommunityImg,Integer> {

    public List<BoardCommunityImg> findByBoardNo(int boardNo);

}