package com.spring.project3rd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MainController {

    // 메인 페이지
    @GetMapping("/")
    public String index(){
        return "index";
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String login(){
        return "login";
    }

    // 커뮤니티 게시판 메인
    @GetMapping("/board/community")
    public String boardCommunity(){
        return "board_community";
    }

    @GetMapping("/board/community/write")
    public String boardCommunityWrite(@CookieValue(value = "accessToken", required = false) String accessToken){
        if(accessToken!=null){
            return "board_community_write";
        }
        return "login";
    }

}


// 커뮤니티 게시판 - 게시글 작성(세션이용)
    /*@GetMapping("/board/community/write")
    public String boardCommunityWrite(WebRequest request){
        // 세션에 있는 로그인 정보 가져오기
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        System.out.println("log" + log);
        // 로그인 확인
        if(log!=null){
            return "board_community_write";
        }

        return "login";
    }*/
