package com.spring.project3rd.domain.boardCommunity;

import com.spring.project3rd.domain.boardFree.BoardFree;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommunityRepository extends JpaRepository<BoardCommunity,Integer> {
    // 게시판 목록 - 제목 검색
    public List<BoardCommunity> findByTitleContaining(String keyword, Pageable pageable);
}
