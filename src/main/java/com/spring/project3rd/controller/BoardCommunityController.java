package com.spring.project3rd.controller;

import com.spring.project3rd.domain.boardCommunity.BoardCommunity;
import com.spring.project3rd.domain.boardCommunity.BoardCommunityRepository;
import com.spring.project3rd.domain.boardCommunity.BoardCommunityRequestDto;
import com.spring.project3rd.payload.Response;
import com.spring.project3rd.service.BoardCommunityService;
import com.spring.project3rd.util.Timestamp;
import lombok.RequiredArgsConstructor;


import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("api/board/community")
public class BoardCommunityController{

    private final BoardCommunityRepository boardCommunityRepository;
    private final BoardCommunityService boardCommunityService;

    // Read : 커뮤니티 게시판 전체 조회
    @GetMapping("list/{page}")
    public ModelAndView showList(@PathVariable("page") int page,
                                 @RequestParam(defaultValue = "") String keyword,
                                 @PageableDefault(size = 5) Pageable pageable){
        ModelAndView view = new ModelAndView("board_community_list");
        List<BoardCommunity> list=new ArrayList<>();
        if(!keyword.isEmpty()){
            list = boardCommunityRepository.findByTitleContaining(keyword, pageable.withPage(page-1));
        }else{
            list=boardCommunityRepository.findAll(pageable.withPage(page-1)).getContent();
        }

        // 가져온 리스트를 view에 저장
        view.addObject("list",list);

        return view;
    }

    // Create : 게시글 작성          <--- 추후 로그인 확인부분 넣을 것
//    @ResponseBody <--- RestController : JSON Body 탐색 / Controller : JSP 파일 탐색
    @PostMapping(value="write", consumes={"multipart/form-data"})
    public Response boardWrite(@ModelAttribute BoardCommunityRequestDto bcDto, WebRequest request){

        BoardCommunity bc=new BoardCommunity(bcDto);

        boardCommunityRepository.save(bc);

        return new Response("post", "success");
    }
}
