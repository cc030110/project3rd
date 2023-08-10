package com.spring.project3rd.controller;

import com.spring.project3rd.domain.boardFree.BoardFree;
import com.spring.project3rd.domain.boardFree.BoardFreeRepository;
import com.spring.project3rd.service.BoardFreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;



@Controller
@RequiredArgsConstructor
@RequestMapping("board/free/")
public class BoardFreeController {

    private final BoardFreeRepository boardFreeRepository;
    private final BoardFreeService boardFreeService;

    // 게시글 목록
    // 시작 페이지1, 검색어 없을 경우 ""
    @GetMapping("list/{page}")
    public ModelAndView showList(@PathVariable("page") int page,
                                 @RequestParam(defaultValue = "") String keyword,
                                 @PageableDefault(size = 5) Pageable pageable) {
        ModelAndView view = new ModelAndView    ("board_free_list");
        List<BoardFree> list = new ArrayList<>();
        if (!keyword.isEmpty()) {
            list = boardFreeRepository.findByTitleContaining(keyword, pageable.withPage(page - 1));
        } else {
            list = boardFreeRepository.findAll(pageable.withPage(page - 1)).getContent();
        }
        // 가져온 리스트를 view에 저장
        view.addObject("list",list);

        return view;
    }

    // 게시글 등록
    @GetMapping("upload")
    public String uploadPage(){
        // 로그인 확인 필요
        return "board_free_upload";
    }
//    @PostMapping("upload")
//    public String upload(){
//
//    }

}
