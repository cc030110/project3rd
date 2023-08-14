package com.spring.project3rd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

@Controller
public class MainController {

    // 메인 페이지
    @GetMapping("/")
    public String index(){
        return "index";
    }

    // 로그인 페이지
    @GetMapping("login")
    public String login(){
        return "login";
    }

    // 회원가입
    @GetMapping("api/user/join")
    public String showJoinForm() {
        return "user_join";
    }

    // 커뮤니티 게시판 - 게시글 작성
    @GetMapping("/board/community/write")
    public String boardCommunityWrite(WebRequest request){
        // 세션에 있는 로그인 정보 가져오기
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        System.out.println("log" + log);
        // 로그인 확인
        if(log!=null){
            return "board_community_write";
        }

        return "login";
    }

    // 자유게시판 - 게시글 업로드
    @GetMapping("board/free/upload")
    public String uploadPage(WebRequest request){
        // 세션에 있는 로그인 정보 가져오기
        String log = (String) request.getAttribute("log",WebRequest.SCOPE_SESSION);
        System.out.println("log:"+log); // 확인용
        // 로그인 확인
        if(log!=null){
            return "board_free_upload";
        }
        return "login";
    }


}
