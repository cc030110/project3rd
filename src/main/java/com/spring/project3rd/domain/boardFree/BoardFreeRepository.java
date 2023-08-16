package com.spring.project3rd.domain.boardFree;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardFreeRepository extends JpaRepository<BoardFree,Integer> {

    // 게시판 전체 목록 (제목 검색)
    public List<BoardFree> findByTitleContaining(String keyword, Pageable pageable);

    // 게시판 목록 - 검색 X, 특정 유저(정지 및 블락) 제외
    public Page<BoardFree> findByIdNotIn(List<String> excludedIds, Pageable pageable);

    // 게시판 목록 - 제목 검색(특정 유저 제외)
    public Page<BoardFree> findByTitleContainingAndIdNotIn(String title, List<String> excludedIds, Pageable pageable);

    // 게시판 목록 - 작성자 검색(특정 유저 제외)
    public Page<BoardFree> findByIdContainingAndIdNotIn(String id, List<String> excludedIds, Pageable page);

    // 게시판 목록 -

}
