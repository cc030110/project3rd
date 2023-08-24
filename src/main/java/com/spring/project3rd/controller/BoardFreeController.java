package com.spring.project3rd.controller;

import com.spring.project3rd.domain.boardCommunity.BoardCommunity;
import com.spring.project3rd.domain.boardCommunity.BoardCommunityRequestDto;
import com.spring.project3rd.domain.boardCommunityImg.BoardCommunityImg;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/free/")
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
    public ModelAndView showList(@PathVariable("page") int page,
                                    @RequestParam(required = false) String title,
                                    @RequestParam(required = false) String author,
                                    @PageableDefault(sort = "boardNo", direction = Sort.Direction.DESC) Pageable pageable,
                                    @CookieValue(value = "accessToken", required = false) String accessToken) {
        // 페이지 정렬(sort) -> boardNo를 기준으로, DECS(내림차순)
        int pageSize = 10; // pageable의 기본 size가 10 이므로 굳이 바꿔줄 필요 X

        // board_free_list로 해당 정보 가져감
        ModelAndView view = new ModelAndView("board_free_list");

        // 게시글 목록
        Page<BoardFree> getBoardList = null;

        // 제외할 게시글 작성자 목록
        List<String> excludeIds = new ArrayList<>();

        // is_active = 0 인 유저 제외
        excludeIds = userService.inactiveUserIds();

        // 로그인 중일 경우 차단한 유저 제외
        if (accessToken != null) {
            Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
            String id = claims.get("id", String.class);
            // 로그인 중인 아이디도 view에 저장
            view.addObject("id",id);
            // id로 차단목록 가져오기
            List<String> blockList = blockService.blockList(id);
            // 해당 유저가 차단한 유저가 존재할 경우
            if (!blockList.isEmpty()) {
                excludeIds.addAll(blockList);
            }
        }

        // 검색어 구분
        if (title != null && !title.isEmpty()) { // 제목 검색
            if(excludeIds.isEmpty()){
                getBoardList = boardFreeRepository.findByTitleContaining(title,pageable.withPage(page-1));
            }else{
                getBoardList = boardFreeRepository.findByTitleContainingAndIdNotIn(title, excludeIds, pageable.withPage(page - 1));
            }
        } else if (author != null && !author.isEmpty()) { // 작성자 검색
            if(excludeIds.isEmpty()){
                getBoardList = boardFreeRepository.findByNameContaining(author,pageable.withPage(page-1));
            }else{
                getBoardList = boardFreeRepository.findByNameContainingAndIdNotIn(author, excludeIds, pageable.withPage(page - 1));
            }
        } else { // 검색 없음
            if(excludeIds.isEmpty()){
                getBoardList = boardFreeRepository.findAll(pageable.withPage(page-1));
            }else{
                getBoardList = boardFreeRepository.findByIdNotIn(excludeIds, pageable.withPage(page - 1));
            }
        }

        // 게시판 리스트 view에 추가 (boardList는 Page<BoardFree> 타입, 페이지 정보도 포함)
        view.addObject("boardList", getBoardList);

        int totalPages = getBoardList.getTotalPages();
        int currentPageGroup = (page - 1) / pageSize;
        int startPage = currentPageGroup * pageSize + 1;
        int endPage = Math.min(startPage + pageSize - 1 , totalPages);

        view.addObject("startPage", startPage);
        view.addObject("endPage", endPage);

        return view;
    }


    /* 게시글 업로드 */
    // 자유게시판 업로드
    @GetMapping("upload")
    public ModelAndView uploadPage(@CookieValue(value = "accessToken", required = false) String accessToken){
        ModelAndView view = new ModelAndView("board_free_upload");
        String id="";
        String name="";
        if(accessToken!=null){
            Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
            id = claims.get("id",String.class);
            name = claims.get("name",String.class);
            System.out.println("id:"+id);
            System.out.println("name:"+name);
        }else{
            return new ModelAndView(new RedirectView("/user/login")); // 해당 path의 getmapping으로 이동
        }
        view.addObject("id",id);
        view.addObject("name",name);
        return view;
    }
    @PostMapping("upload")
    public BoardFree upload(@RequestBody BoardFreeRequestDto boardDto,
                            @CookieValue(value = "accessToken", required = false) String accessToken) {
        BoardFree board = null;
        if (accessToken != null && boardDto.getTitle() != null && boardDto.getContents() != null) {
            Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
            String id = claims.get("id", String.class);
            String name = claims.get("name",String.class);
            boardDto.setId(id);
            boardDto.setName(name);
            board = new BoardFree(boardDto);
            boardFreeRepository.save(board);
        }
        return board;
    }
    // 이미지 업로드
    @PostMapping(value = "upload/file", consumes = {"multipart/form-data"})
    public List<Response> uploadImgFile(@RequestParam("no") int boardNo, @RequestParam("img") List<MultipartFile> files) {
        List<Response> responses = new ArrayList<>();
        List<String> urls = uploadFileService.uploadImgFiles(files);
        System.out.println(urls);
        if (urls != null && !urls.isEmpty()) {
            // DB에 저장
            for (String url : urls) {
                BoardFreeImg img = new BoardFreeImg(boardNo, url);
                boardFreeImgRepository.save(img);
                responses.add(new Response(url, "success"));
            }
        } else {
            responses.add(new Response("urls are null", "fail"));
        }
        return responses;
    }
    /* ---- */

    /* 게시글 조회 */
    @GetMapping("{no}")
    public ModelAndView showBoard(@PathVariable int no, @CookieValue(value = "accessToken", required = false) String accessToken) {
        ModelAndView view = new ModelAndView("board_free");

        Optional<BoardFree> optionalBoard = boardFreeRepository.findById(no);
        BoardFree board = optionalBoard.orElse(null);

        if (board != null) {
            // 게시글의 조회수 1 증가
            boardFreeService.addViews(board);
            // 게시글에 업로드된 이미지 확인
            List<BoardFreeImg> imgList = boardFreeImgRepository.findByBoardNo(board.getBoardNo());
            // 업로드된 이미지가 있을 경우 view에 추가
            if (!imgList.isEmpty()) {
                view.addObject("imgList", imgList);
            }
            // 로그인 상태 확인 및 전달
            if (accessToken != null) {
                Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
                String id = claims.get("id", String.class);
                view.addObject("id", id);
            }
        }
        view.addObject("board", board);
        return view;
    }
    /* ---- */


    /* 게시글 삭제 */
    @DeleteMapping("{no}/delete")
    public ResponseEntity<String> deleteBoard(@PathVariable("no") int no) {
        boardFreeService.deleteBoard(no);
        return ResponseEntity.ok("게시글 삭제 성공");
    }

    /* ---- */
    // 게시글 수정
    // 업데이트용 게시판 조회
    @GetMapping("/{boardNum}/update")
    public ModelAndView showBoardForUpdate(@PathVariable int boardNum){
        ModelAndView view = new ModelAndView("board_free_update");

        Optional<BoardFree> optionalBoardUpdate = boardFreeRepository.findById(boardNum);
        BoardFree board = optionalBoardUpdate.orElse(null);

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

    @PutMapping(value="/{boardNo}/update")
    public Response boardUpdate(@RequestBody BoardFreeRequestDto bfDto,
                                @PathVariable int boardNo,
                                @CookieValue(value="accessToken",required = false) String accessToken){
        Claims claims=jwtTokenizer.parseToken(accessToken,jwtTokenizer.accessSecret);
        String id=claims.get("id",String.class);
        String name=claims.get("name",String.class);

        short modifyCheck=1;

        if(id==null){
            return new Response("update","fail:have to log in");
        }

        bfDto.setId(id);
        bfDto.setIsModified(modifyCheck);

        boardFreeService.updateBoardByBoardNo(boardNo,name,bfDto);

        return new Response("Board Update","success");
    }


    /*@GetMapping("{no}/update")
    public ModelAndView boardFreeUpdate(@PathVariable("no")int no,@CookieValue(value = "accessToken", required = false) String accessToken){
        ModelAndView view = new ModelAndView("board_free_update");

        String id="";
        if(accessToken!=null){
            Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
            id = claims.get("id",String.class);
        }

        BoardFree board = boardFreeRepository.findById(no).orElse(null);

        // err 존재할 경우 html onload 시 해당 메세지를 출력 후 다른 페이지로 이동
        if(board==null){
            view.addObject("err","dose_not_exist");
        }else if(id.isEmpty()){
            view.addObject("err","need_login");
        }else if(!id.equals(board.getId())){
            view.addObject("err","not_author");
        }else{
            view.addObject("board",board);
        }

        return view;
    }

    @PutMapping("{no}/update")
    public ResponseEntity<String> updateBoard(@PathVariable("no") int no, @RequestBody BoardFreeRequestDto boardDto) {
        try {
            boardFreeService.updateBoard(no,boardDto);
            return ResponseEntity.status(HttpStatus.OK).body("게시글 수정 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트 에러");
        }
    }*/


}