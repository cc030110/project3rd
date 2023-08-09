package com.spring.project3rd.domain.boardFree;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardFreeRepository extends JpaRepository<BoardFree,Integer> {

    // 게시판 목록 - 제목 검색
    public List<BoardFree> findByTitleContaining(String keyword, Pageable pageable);

}
