package com.spring.project3rd.controller;

import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    // 커뮤니티 게시판 메인 : 추후 수정 혹은 삭제 : 인규
    @GetMapping("/board/community")
    public String boardCommunity(){
        return "board_community_list";
    }

    @GetMapping("/board/community/write")
    public String boardCommunityWrite(){
        return "board_community_write";
    }


}
