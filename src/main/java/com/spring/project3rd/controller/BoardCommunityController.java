package com.spring.project3rd.controller;

import com.spring.project3rd.domain.boardCommunity.BoardCommunity;
import com.spring.project3rd.domain.boardCommunity.BoardCommunityRepository;
import com.spring.project3rd.domain.boardCommunity.BoardCommunityRequestDto;
import com.spring.project3rd.domain.boardCommunityImg.BoardCommunityImg;
import com.spring.project3rd.domain.boardCommunityImg.BoardCommunityImgRepository;
import com.spring.project3rd.domain.participant.Participant;
import com.spring.project3rd.domain.platform.Platform;
import com.spring.project3rd.domain.platform.PlatformRepository;
import com.spring.project3rd.domain.user.User;
import com.spring.project3rd.domain.user.UserRepository;
import com.spring.project3rd.payload.Response;
import com.spring.project3rd.security.jwt.util.JwtTokenizer;
import com.spring.project3rd.service.*;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("board/community")
public class BoardCommunityController{

    private final BoardCommunityRepository boardCommunityRepository;
    private final BoardCommunityService boardCommunityService;
    private final BoardCommunityImgRepository boardCommunityImgRepository;

    private final UserService userService;
    private final BlockService blockService;
    private final UploadFileService uploadFileService;
    private final ParticipantService participantService;


    private final PlatformRepository platformRepository;
    private final UserRepository userRepository;


    private final JwtTokenizer jwtTokenizer;

    // 게시글 번호로 게시글 가져오기
    public BoardCommunity getBoardByBoardNo(int boardNo){
        BoardCommunity bc=boardCommunityRepository.findById(boardNo).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        return bc;
    }

    // # Read
    // 게시판 목록 조회(제목,작성자,플랫폼)
    @GetMapping("/main/{page}")
    public ModelAndView showList(@PathVariable("page") int page,
                                 @RequestParam(required = false) String title,
                                 @RequestParam(required = false) String author,
                                 @RequestParam(required = false) String platformName,
                                 @PageableDefault(size=5, sort="boardNo", direction = Sort.Direction.DESC) Pageable pageable,
                                 @CookieValue(value="accessToken", required = false) String accessToken){

        // 페이지 정렬(sort) -> boardNo를 기준으로 DESC 정렬
        int pageSize = 5;

        // 해당 정보 가져오기
        ModelAndView view = new ModelAndView("board_community_main");

        // 게시글 목록
        Page<BoardCommunity> getBoardList = null;

        // 제외할 게시글 작성자 목록
        List<String> excludeIds = new ArrayList<>();

        // 탈퇴유저 제외(is_active=0)
        excludeIds = userService.inactiveUserIds();

        // 로그인한 회원의 차단 유저 제외
        if(accessToken != null){
            Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
            String id = claims.get("id",String.class);
//            String name = claims.get("name",String.class);

            // 로그인 중인 아이디 view에 저장
            view.addObject("id",id);
//            view.addObject("name",name);

            // id로 차단목록 가져오기
            List<String> blockList=blockService.blockList(id);

            // 해당 유저가 존재한다는 조건
            if(!blockList.isEmpty()){
                excludeIds.addAll(blockList);
            }
        }

        // 검색 조건
        if(title!=null && !title.isEmpty()){
            if(excludeIds.isEmpty()){
                getBoardList=boardCommunityRepository.findByTitleContaining(title,pageable.withPage(page-1));
            }else{
                getBoardList=boardCommunityRepository.findByTitleContainingAndIdNotIn(title, excludeIds, pageable.withPage(page-1));
            }
        }else if(author != null && !author.isEmpty()){
            if(excludeIds.isEmpty()){
                getBoardList=boardCommunityRepository.findByNameContaining(author,pageable.withPage(page-1));
            }else{
                getBoardList=boardCommunityRepository.findByNameContainingAndIdNotIn(author, excludeIds, pageable.withPage(page-1));
            }
        }else if(platformName != null && !platformName.isEmpty()){
            if(excludeIds.isEmpty()){
                getBoardList=boardCommunityRepository.findByPlatformNameContaining(platformName, pageable.withPage(page-1));
            }else{
                getBoardList=boardCommunityRepository.findByPlatformNameContainingAndIdNotIn(platformName,excludeIds,pageable.withPage(page-1));
            }
        }else{ // 검색을 하지 않을 경우(ex>초기화면)
            if(excludeIds.isEmpty()){
                getBoardList=boardCommunityRepository.findAll(pageable.withPage(page-1));
            }else{
                getBoardList=boardCommunityRepository.findByIdNotIn(excludeIds, pageable.withPage(page-1));
            }
        }

        // 게시판 리스트 view에 추가(boardList는 Page<BoardCommunity> 타입, 페이지 정보도 포함)
        view.addObject("boardList", getBoardList);

        // 해당 게시판의 참가자 정보도 넣기 (수락(1)된 리스트)
        List<BoardCommunity> boardList = getBoardList.getContent();
        if(!boardList.isEmpty()){
            // boardNo, 참가자 수
            Map<Integer,Integer> acceptedNum = new HashMap<>();
            for(BoardCommunity board : boardList){
                int boardNo = board.getBoardNo();
                List<Participant> p = participantService.getListByBoardNoAndAccept(boardNo,1);
                acceptedNum.put(boardNo,p.size());
            }
            view.addObject("acceptedNum",acceptedNum);
        }


        // 플랫폼 객체 가져오기
        Map<String,String> platforms = new HashMap<>();
        List<Platform> platformList = platformRepository.findAll();
        for (Platform platform : platformList) {
            platforms.put(platform.getPlatformName(), platform.getPlatformImg());
        }

        view.addObject("platform", platforms);  // 플랫폼을 view에 저장

        // 페이지 쪼개기
        int totalPages = getBoardList.getTotalPages();
        int currentPageGroup = (page-1)/pageSize;
        int startPage = currentPageGroup * pageSize +1;
        int endPage = Math.min(startPage + pageSize -1, totalPages);

        view.addObject("startPage",startPage);
        view.addObject("endPage",endPage);

//        System.out.println("Page<T> : "+getBoardList);

        return view;
    }

    // 커뮤니티 게시판 게시글 한개 조회
    @GetMapping("/{boardNum}")
    public ModelAndView showBoard(@PathVariable int boardNum,
                                  @CookieValue(value = "accessToken", required = false) String accessToken){
        ModelAndView view = new ModelAndView("board_community");

        Optional<BoardCommunity> optionalBoard = boardCommunityRepository.findById(boardNum);
        BoardCommunity board = optionalBoard.orElse(null);

        if(board!=null){
            int boardNo = board.getBoardNo();
            boardCommunityService.addViews(board);  // 게시글 조회수 증가
            List<BoardCommunityImg> imgList = boardCommunityImgRepository.findByBoardNo(boardNo);

            // 해당 게시글에 업로드된 파일이 존재할 경우
            if(!imgList.isEmpty()){
                view.addObject("imgList",imgList);
            }

            // 로그인 상태 확인 및 전달
            if(accessToken!=null){
                Claims claims=jwtTokenizer.parseToken(accessToken,jwtTokenizer.accessSecret);
                String id=claims.get("id",String.class);
                view.addObject("id",id);
            }

            // 게시글의 참가 수락 목록 전달
            List<Participant> participants = participantService.getListByBoardNoAndAccept(boardNo, 1);
            // 해당 목록에서 id만 가져와 -> name으로 가져옴
            if(!participants.isEmpty()){
                List<String> names = new ArrayList<>();
                for(Participant p : participants){
                    String id = p.getParticipantId();
                    String name = userService.getUserName(id);
                    names.add(name);
                }
                view.addObject("names",names);
            }
        }
        view.addObject("board",board);

        // 플랫폼 객체 가져오기
        Map<String,String> platforms = new HashMap<>();
        List<Platform> platformList = platformRepository.findAll();
        for (Platform platform : platformList) {
            platforms.put(platform.getPlatformName(), platform.getPlatformImg());
        }

        view.addObject("platform", platforms);  // 플랫폼을 view에 저장

        return view;
    }

    // 업데이트용 게시판 조회
    @GetMapping("/{boardNum}/update")
    public ModelAndView showBoardForUpdate(@PathVariable int boardNum){
        ModelAndView view = new ModelAndView("board_community_update");

        Optional<BoardCommunity> optionalBoardUpdate = boardCommunityRepository.findById(boardNum);
        BoardCommunity board = optionalBoardUpdate.orElse(null);

        view.addObject("board",board);

        if(board!=null){
            int boardNo = board.getBoardNo();
            List<BoardCommunityImg> imgList = boardCommunityImgRepository.findByBoardNo(boardNo);
            // 해당 게시글에 업로드된 파일이 존재할 경우
            if(!imgList.isEmpty()){
                view.addObject("imgList",imgList);
            }
        }
        return view;
    }

    // 게시글 작성
    @PostMapping("/write")
    public BoardCommunity boardWrite(@RequestBody BoardCommunityRequestDto bcDto,
                                     @CookieValue(value ="accessToken",required = false) String accessToken){
        BoardCommunity bc=null;
        if(accessToken!=null && bcDto.getTitle()!=null && bcDto.getContents()!=null){
            Claims claims=jwtTokenizer.parseToken(accessToken,jwtTokenizer.accessSecret);
            String id=claims.get("id", String.class);
            String name=claims.get("name",String.class);

            bcDto.setId(id);
            bcDto.setName(name);

            bc = new BoardCommunity(bcDto);
            boardCommunityRepository.save(bc);
        }
        return bc;
    }

    // 게시글 이미지 첨부
    @PostMapping(value="write/file", consumes={"multipart/form-data"})
    public List<Response> uploadImgFile(@RequestParam("boardNo") int boardNo, @RequestParam("img") List<MultipartFile> files){
        List<Response> responses = new ArrayList<>();
        List<String> urls=uploadFileService.uploadImgFiles(files);
        System.out.println(urls);

        if(urls!=null && !urls.isEmpty()){
            // DB 저장
            for(String url : urls){
                BoardCommunityImg img = new BoardCommunityImg(boardNo,url);
                boardCommunityImgRepository.save(img);
                responses.add(new Response(url,"success"));
            }
        }else{
            responses.add(new Response("urls are null","fail"));
        }
        return responses;
    }

    // # Update
    // 게시글 수정
//    @PutMapping(value="/update/{boardNo}", consumes={"multipart/form-data"})
    /*public Response boardUpdate(@PathVariable int boardNo, @ModelAttribute BoardCommunityRequestDto bcDto,
                                @CookieValue(value="accessToken",required = false) String accessToken){*/
    @PutMapping(value="/update/{boardNo}")
    public Response boardUpdate(@RequestBody BoardCommunityRequestDto bcDto,
                                @PathVariable int boardNo,
                                @CookieValue(value="accessToken",required = false) String accessToken){
        Claims claims=jwtTokenizer.parseToken(accessToken,jwtTokenizer.accessSecret);
        String id=claims.get("id",String.class);
        String name=claims.get("name",String.class);

        short modifyCheck=1;

        if(id==null){
            return new Response("update","fail:have to log in");
        }

        bcDto.setId(id);
        bcDto.setIsModified(modifyCheck);

        boardCommunityService.updateBoardByBoardNo(boardNo,name,bcDto);

        return new Response("Board Update","success");
    }

    // 게시글 삭제
    @DeleteMapping(value="/delete/{boardNo}")
    public Response boardDelete(@PathVariable int boardNo,
                                @CookieValue(value = "accessToken", required = false) String accessToken){

        Claims claims=jwtTokenizer.parseToken(accessToken,jwtTokenizer.accessSecret);
        String id=claims.get("id",String.class);
        System.out.println(id);

        if(id==null){
            return new Response("Board Delete","Fail");
        }

        BoardCommunity bc=getBoardByBoardNo(boardNo);
        if(!bc.getId().equals(id)){
            return new Response("delete","fail : not correct user");
        }

        boardCommunityService.deleteBoardByBoardNo(boardNo);

        return new Response("delete","success");
    }
}

// 게시판 리스트 view에 추가
/* List<BoardCommunity> boardCommunityList = getBoardList.getContent();
        System.out.println("boardCommunityList : "+boardCommunityList);

        // 가져온 리스트가 하나라도 있을 경우
        if(!boardCommunityList.isEmpty()){
            Map<String,String> authorList = new HashMap<>();
            for(BoardCommunity boardCommunity : boardCommunityList){
                String id = boardCommunity.getId();
                String name=userService.getUserName(id);
                authorList.put(id,name);
            }

            // 게시판 리스트 작성 유저의 name 리스트 view에 추가
            view.addObject("authorList",authorList);
        }*/