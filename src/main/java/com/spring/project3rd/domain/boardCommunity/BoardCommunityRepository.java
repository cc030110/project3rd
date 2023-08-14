// 문서 읽기를 생활화합시다.

package com.spring.project3rd.domain.boardCommunity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommunityRepository extends JpaRepository<BoardCommunity,Integer> {
    // 게시판 목록 - 제목 검색
    public List<BoardCommunity> findByTitleContaining(String keyword, Pageable pageable);

    // 게시판 글 1개 불러오기
//    public BoardCommunity findByBoardNoAndId(int boardNo, String id);       // creation은 내 맘대로 메소드를 정하는게 아니다 : 문서보고 만들 것
    public BoardCommunity getBoardCommunityByBoardNoAndId(int boardNo, String id);
}