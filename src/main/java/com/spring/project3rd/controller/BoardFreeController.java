package com.spring.project3rd.controller;

import com.spring.project3rd.domain.boardFree.BoardFree;
import com.spring.project3rd.domain.boardFree.BoardFreeRepository;
import com.spring.project3rd.domain.boardFree.BoardFreeRequestDto;
import com.spring.project3rd.domain.boardImg.BoardFreeImg;
import com.spring.project3rd.domain.boardImg.BoardFreeImgRepository;
import com.spring.project3rd.domain.boardImg.BoardFreeImgRequestDto;
import com.spring.project3rd.service.BoardFreeService;
import com.spring.project3rd.util.Uploadcare;
import com.uploadcare.upload.UploadFailureException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("board/free/")
public class BoardFreeController {

    private final BoardFreeRepository boardFreeRepository;
    private final BoardFreeService boardFreeService;
    private final Uploadcare uploadcare;

    private final BoardFreeImgRepository boardFreeImgRepository;

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



    // 게시글 업로드
    @PostMapping("upload")
    public BoardFree upload(@RequestBody BoardFreeRequestDto boardDto){
        System.out.println(boardDto);
        BoardFree board = null;

        if(boardDto.getId()!=null&&boardDto.getTitle()!=null&&boardDto.getContents()!=null){
            board = new BoardFree(boardDto);
            boardFreeRepository.save(board);
        }

        return board;
    }



    // 파일 업로드
    @PostMapping(value = "upload/file", consumes = {"multipart/form-data"})
    public BoardFreeImgRequestDto uploadImgFile(@ModelAttribute BoardFreeImgRequestDto file){
        String fileName = file.getImg().getName();
        System.out.println("fileName:"+fileName);

//            try {
//                String path = "test." + img.getImg().getContentType().split("/")[1];
//                byte[] image = img.getImg().getBytes();
//                File file = new File(path);
//                OutputStream os = new FileOutputStream(file);
//                os.write(image);
//                imgUrl = uploadcare.getUploadFileUrl(path);
//                // 디렉토리에 만들어진 파일 삭제처리 필요
//                os.close();
//            } catch (IOException | UploadFailureException e) {
//                throw new RuntimeException(e);
//            }

        return file;
    }


    // 게시글 하나 확인
    @GetMapping("{no}")
    public ModelAndView showBoard(@PathVariable int no){
        ModelAndView view = new ModelAndView("board_free");

        Optional<BoardFree> optionalBoard = boardFreeRepository.findById(no);
        BoardFree board = optionalBoard.orElse(null);

        view.addObject("board",board);

        if(board!=null){
            int boardNo = board.getBoardNo();
            List<BoardFreeImg> imgList = boardFreeImgRepository.findByBoardNo(boardNo);
            // 해당 게시글에 업로드된 파일이 존재할 경우
            if(!imgList.isEmpty()){
                view.addObject("imgList",imgList);
            }
        }

        return view;
    }




}
