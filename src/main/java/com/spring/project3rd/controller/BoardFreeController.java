package com.spring.project3rd.controller;

import com.spring.project3rd.domain.boardFree.BoardFree;
import com.spring.project3rd.domain.boardFree.BoardFreeRepository;
import com.spring.project3rd.service.BoardFreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("board/free/")
public class BoardFreeController {

//    private final BoardFreeRepository boardFreeRepository;
//    private final BoardFreeService boardFreeService;

    // 게시글 목록
    // GET - board/free/list/{page}
    // 시작 페이지1, 검색어 없을 경우 ""
//    @GetMapping("list/{page}")
//    public ModelAndView showList(@PathVariable("page") int page,
//                                 @RequestParam(defaultValue = "") String keyword,
//                                 @PageableDefault(size = 5) Pageable pageable){
//        ModelAndView view = new ModelAndView("board_free_list");
//        List<BoardFree> list = null;
//        if (!keyword.isEmpty()) {
//            list = boardFreeRepository.findByTitleContaining(keyword,pageable.withPage(page-1));
//        } else {
//            list = boardFreeRepository.findAll(pageable.withPage(page-1)).getContent();
//        }
//        return view;
//    }


}
