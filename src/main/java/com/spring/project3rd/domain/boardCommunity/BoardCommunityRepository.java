// 문서 읽기를 생활화합시다.

package com.spring.project3rd.domain.boardCommunity;

import com.spring.project3rd.domain.boardFree.BoardFree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommunityRepository extends JpaRepository<BoardCommunity,Integer> {
    // 게시판 전체 목록 - 제목 검색
    Page <BoardCommunity> findByTitleContaining(String keyword, Pageable pageable);

    // 게시판 전체 목록 - 작성자 검색
    Page<BoardCommunity> findByNameContaining(String name, Pageable pageable);

    // 게시판 전체목록 - 플랫폼 검색
    Page<BoardCommunity> findByPlatformNameContaining(String platformName, Pageable pageable);

    // 게시판 목록 - 차단 유저 제외
    Page<BoardCommunity> findByIdNotIn(List<String> excludedIds, Pageable pageable);

    // 게시판 목록 - 제목 검색 + 차단유저 제외
    Page<BoardCommunity> findByTitleContainingAndIdNotIn(String title, List<String> excludedIds, Pageable pageable);

    // 게시판 목록 - 작성자 검색 + 차단유저 제외
    Page<BoardCommunity> findByNameContainingAndIdNotIn(String name, List<String> excludedIds, Pageable pageable);

    // 게시판 목록 - 플랫폼 검색 + 차단 유저 제외
    Page<BoardCommunity> findByPlatformNameContainingAndIdNotIn(String platformName, List<String> excludeIds, Pageable pageable);

    // 게시판 글 1개 불러오기
//    public BoardCommunity findByBoardNoAndId(int boardNo, String id);       // creation은 내 맘대로 메소드를 정하는게 아니다 : 문서보고 만들 것
    public BoardCommunity getBoardCommunityByBoardNoAndName(int boardNo, String name);


    // 유저가 작성한 게시글 목록
    List<BoardCommunity> findAllById(String id);
}
