package com.spring.project3rd.controller;

import com.spring.project3rd.domain.boardFree.BoardFree;
import com.spring.project3rd.domain.boardFree.BoardFreeRepository;
import com.spring.project3rd.domain.boardFree.BoardFreeRequestDto;
import com.spring.project3rd.domain.boardFreeImg.BoardFreeImg;
import com.spring.project3rd.domain.boardFreeImg.BoardFreeImgRepository;
import com.spring.project3rd.payload.Response;
import com.spring.project3rd.security.jwt.util.JwtTokenizer;
import com.spring.project3rd.service.BlockService;
import com.spring.project3rd.service.BoardFreeService;
import com.spring.project3rd.service.UploadFileService;
import com.spring.project3rd.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("board/free/")
public class BoardFreeController {

    private final BoardFreeRepository boardFreeRepository;
    private final BoardFreeService boardFreeService;
    private final BoardFreeImgRepository boardFreeImgRepository;

    private final UserService userService;
    private final BlockService blockService;

    private final UploadFileService uploadFileService;
    private final JwtTokenizer jwtTokenizer;

    // 게시글 목록
    // 시작 페이지 1, 제목/작성자 검색
    @GetMapping("list/{page}")
    public Page<BoardFree> showList(@PathVariable("page") int page,
                                    @RequestParam(required = false) String title,
                                    @RequestParam(required = false) String author,
                                    @PageableDefault(size = 5) Pageable pageable,
                                    @CookieValue(value = "accessToken", required = false) String accessToken) {
        // board_free_list로 해당 정보 가져감
//        ModelAndView view = new ModelAndView("board_free_list");

        // 게시글 목록
        Page<BoardFree> boardList = null;

        // 제외할 게시글 작성자 목록
        List<String> excludeIds = new ArrayList<>();

        // is_active = 0 인 유저 제외
        excludeIds = userService.inactiveUserIds();

        // 로그인 중일 경우 차단한 유저 제외
        if(accessToken!=null){
            Claims claims = jwtTokenizer.parseToken(accessToken,jwtTokenizer.accessSecret);
            String id = claims.get("id",String.class);
            List<String> blockList = blockService.blockList(id);
            // 해당 유저가 차단한 유저가 존재할 경우
            if(!blockList.isEmpty()){
                excludeIds.addAll(blockList);
            }
        }

        boardList = boardFreeRepository.findByIdNotIn(excludeIds,pageable.withPage(page-1));

//        // 검색어 구분
//        if(title != null && !title.isEmpty()){ // 제목 검색
////            boardList =
//        }else if(author != null && !author.isEmpty()){ // 작성자 검색
//
//        }else{
//
//        }
//        return view;

        return boardList;

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