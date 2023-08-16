package com.spring.project3rd.controller;

import com.spring.project3rd.domain.boardFree.BoardFree;
import com.spring.project3rd.domain.boardFree.BoardFreeRepository;
import com.spring.project3rd.domain.boardFree.BoardFreeRequestDto;
import com.spring.project3rd.domain.boardFreeImg.BoardFreeImg;
import com.spring.project3rd.domain.boardFreeImg.BoardFreeImgRepository;
import com.spring.project3rd.domain.user.User;
import com.spring.project3rd.payload.Response;
import com.spring.project3rd.service.BoardFreeService;
import com.spring.project3rd.service.UploadFileService;
import com.spring.project3rd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;


@RestController
@RequiredArgsConstructor
@RequestMapping("board/free/")
public class BoardFreeController {

    private final BoardFreeRepository boardFreeRepository;
    private final BoardFreeService boardFreeService;
    private final BoardFreeImgRepository boardFreeImgRepository;
    private final UploadFileService uploadFileService;
    private final UserService userService;


    // 게시글 목록
    // 시작 페이지 1, 제목/작성자 검색
    @GetMapping("list/{page}")
    public ModelAndView showList(@PathVariable("page") int page,
                                 @RequestParam(required = false) String title,
                                 @RequestParam(required = false) String author,
                                 @PageableDefault(size = 5) Pageable pageable,
                                 WebRequest webRequest) {
        // board_free_list로 해당 정보 가져감
        ModelAndView view = new ModelAndView("board_free_list");
//        // 가져올 BoardFree Page<T> 리스트
//        Page<BoardFree> boardList = null;
//
//        // is_active가 0인 유저의 id들
//        List<String> excludingUserList = new ArrayList<>();
//        excludingUserList = userService.getNotActiveUserId();
//
//        System.out.println("제외 id");
//        for(String str : excludingUserList){
//            System.out.println(str);
//        }

        // 만약 로그인 중이라면 excludingUserList에 block한 유저의 id들도 추가
//        String log = (String) webRequest.getAttribute("log",WebRequest.SCOPE_SESSION);
//        if(log!=null){
//
//        }

//        if (!title.isEmpty()) { // 제목 검색
//            boardList = boardFreeRepository.findByTitleContainingAndIdNotIn(title, excludingUserList, pageable.withPage(page - 1));
//        } else if(!author.isEmpty()) { // 작성자 검색
//            boardList = boardFreeRepository.findByIdContainingAndIdNotIn(author, excludingUserList, pageable.withPage(page-1));
//        } else{ // 검색 기능 사용하지 않음
//            boardList = boardFreeRepository.findByIdNotIn(excludingUserList,pageable.withPage(page-1));
//        }
//
//        // 가져온 boardList에서 유저 name만 저장할 리스트
//        List<String> userNameList = new ArrayList<>();
//        if(!boardList.isEmpty()){
//            // 먼저 boardList에서 id List 가져옴
//            List<String> ids = new ArrayList<>();
//            for(BoardFree board : boardList){
//                ids.add(board.getId());
//            }
//            // 해당 id List를 넘겨주어 name List로 리턴해주는 userService의 함수 호출
//            userNameList = userService.getNameListByIdList(ids);
//        }
//
//        // 리스트를 view에 저장
//        view.addObject("boardList",boardList);
//        view.addObject("authorList",userNameList);

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

    // 게시글 이미지 업로드
    @PostMapping(value = "upload/file", consumes = {"multipart/form-data"})
    public List<Response> uploadImgFile(@RequestParam("no") int boardNo, @RequestParam("img") List<MultipartFile> files){
        List<Response> responses = new ArrayList<>();
        List<String> urls = uploadFileService.uploadImgFiles(files);
        System.out.println(urls);
        if(urls!=null&&!urls.isEmpty()){
            // DB에 저장
            for(String url : urls){
                BoardFreeImg img = new BoardFreeImg(boardNo,url);
                boardFreeImgRepository.save(img);
                responses.add(new Response(url,"success"));
            }
        }else{
            responses.add(new Response("urls are null","fail"));
        }
        return responses;
    }

    // 게시글 삭제
    @DeleteMapping("delete/{no}")
    public ResponseEntity<String> deleteBoard(@PathVariable("no") int no){
        boardFreeService.deleteBoard(no);
        return ResponseEntity.ok("게시글 삭제 성공");
    }

    // 게시글 하나 확인
    @GetMapping("{no}")
    public ModelAndView showBoard(@PathVariable int no){
        ModelAndView view = new ModelAndView("board_free");

        Optional<BoardFree> optionalBoard = boardFreeRepository.findById(no);
        BoardFree board = optionalBoard.orElse(null);

        if(board!=null){
            // 해당 board의 views(조회수) 1 증가
            // 코드 작성 필요
            int boardNo = board.getBoardNo();
            List<BoardFreeImg> imgList = boardFreeImgRepository.findByBoardNo(boardNo);
            // 해당 게시글에 업로드된 파일이 존재할 경우
            if(!imgList.isEmpty()){
                view.addObject("imgList",imgList);
            }
        }

        view.addObject("board",board);

        return view;
    }




}
