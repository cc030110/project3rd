package com.spring.project3rd.controller;

import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    // 로그인 페이지 이동
    @GetMapping("login")
    public String login(){
        return "login";
    }

    // 커뮤니티 게시판
    @GetMapping("board/community")
    public String boardCommunity(){
        return "board_community_list";
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

    // 회원가입
    @GetMapping("api/user/join")
    public String showJoinForm() {
        return "user_join";
    }
}
