
package com.spring.project3rd.controller;

import com.spring.project3rd.domain.boardCommunity.BoardCommunity;
import com.spring.project3rd.domain.boardFree.BoardFree;
import com.spring.project3rd.domain.email.BaseResponse;
import com.spring.project3rd.domain.email.EmailCheckReq;
import com.spring.project3rd.domain.participant.Participant;
import com.spring.project3rd.service.*;
import com.spring.project3rd.domain.language.*;
import com.spring.project3rd.domain.user.*;
import com.spring.project3rd.payload.Response;
import com.spring.project3rd.security.jwt.util.JwtTokenizer;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static com.spring.project3rd.security.jwt.util.JwtTokenizer.ACCESS_TOKEN_EXPIRE_COUNT;


@RestController
@RequestMapping("user")
@SpringBootApplication
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UploadFileService uploadFileService;
    private final JwtTokenizer jwtTokenizer;
    private final LanguageRepository languageRepository;
    private final LanguageService languageService;
    private final EmailService emailService;


    private final BoardFreeService boardFreeService;
    private final BoardCommunityService boardCommunityService;
    private final BlockService blockService;
    private final LikeService likeService;
    private final ParticipantService participantService;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository,
                          UploadFileService uploadFileService, JwtTokenizer jwtTokenizer,
                          LanguageRepository languageRepository, LanguageService languageService,
                          EmailService emailService, BoardFreeService boardFreeService, BoardCommunityService boardCommunityService, BlockService blockService, LikeService likeService, ParticipantService participantService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.uploadFileService = uploadFileService;
        this.jwtTokenizer = jwtTokenizer;
        this.languageRepository = languageRepository;
        this.languageService = languageService;
        this.emailService = emailService;
        this.boardFreeService = boardFreeService;
        this.boardCommunityService = boardCommunityService;
        this.blockService = blockService;
        this.likeService = likeService;
        this.participantService = participantService;
    }

    // 로그인
    @PostMapping("login")
    public ResponseEntity login(@RequestBody @Valid MemberLoginDto loginDto, HttpServletResponse response) {

        String id = loginDto.getId();
        String password = loginDto.getPassword();

        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElse(null);

        if (user != null && user.getPassword().equals(loginDto.getPassword())) {
            String name = user.getName();
            String accessToken = jwtTokenizer.createAccessToken(id, name);
//            String refreshToken = JwtTokenizer.createRefreshToken(id);
            // 레디스 저장
//            refreshTokenRepository.save(new RefreshToken(String.valueOf(loginDto.getId()), refreshToken, accessToken));

            MemberLoginResponseDto loginResponse = MemberLoginResponseDto.builder()
                    .accessToken(accessToken)
//                    .refreshToken(refreshToken)
                    .build();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken); // 헤더에 토큰 추가

            Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
            accessTokenCookie.setHttpOnly(true); // JavaScript로 쿠키 접근 방지
            accessTokenCookie.setSecure(true); // HTTPS에서만 전송
            accessTokenCookie.setPath("/"); // 모든 경로에서 접근 가능
            accessTokenCookie.setMaxAge(ACCESS_TOKEN_EXPIRE_COUNT.intValue() / 1000); // 유효기간 설정
            response.addCookie(accessTokenCookie);

            return ResponseEntity.ok().headers(headers).body(loginResponse);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // 로그아웃
    @DeleteMapping("/logout")
    public ResponseEntity logout(HttpServletResponse response) {
        // 만료된 쿠키를 생성하여 현재 시간보다 이전으로 설정
        Cookie expiredAccessTokenCookie = new Cookie("accessToken", "");
        expiredAccessTokenCookie.setMaxAge(0); // 쿠키 기한을 0으로 설정
        expiredAccessTokenCookie.setPath("/");

        // 응답에 만료된 쿠키 추가
        response.addCookie(expiredAccessTokenCookie);

        return new ResponseEntity(HttpStatus.OK);
    }


    /**
     * 회원 가입
     **/
    @GetMapping("/join")
    public ModelAndView JoinForm() {
        ModelAndView view = new ModelAndView("user_join");
        List<Language> languages = languageRepository.findAll();
        // DB에 저장되어있는 언어코드 목록 가져오기
        if (!languages.isEmpty()) {
            List<String> languageCode = new ArrayList<>();
            for (Language language : languages) {
                languageCode.add(language.getLanguageCode());
            }
            view.addObject("languageCode", languageCode);
        }
        return view;
    }

    @PostMapping(value = "/join")
    public Response join(@RequestBody UserRequestDto userRequestDto) {
        List<String> needLang = userRequestDto.getNeedLang();
        List<String> useLang = userRequestDto.getUseLang();

        // 아이디 중복 검사
        String id = userRequestDto.getId();
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return new Response("err", "중복되는 아이디 사용 불가");
        }
        // 이메일 중복 검사
        String email = userRequestDto.getEmail();
        user = userRepository.findByEmail(email);
        if (user != null) {
            return new Response("err", "중복되는 이메일 사용 불가");
        }
        // 이름(닉네임) 중복 검사
        String name = userRequestDto.getName();
        user = userRepository.findByName(name);
        if (user != null) {
            return new Response("err", "중복되는 닉네임 사용 불가");
        }

        User joinUser = new User(userRequestDto);
        try {
            userRepository.save(joinUser);
            // 정상적으로 유저가 DB에 등록되면 useLang과 needLang도 등록
            languageService.setUseLanguage(id, useLang);
            languageService.setNeedLang(id, needLang);
        } catch (Exception e) {
            return new Response("err", "회원가입 실패");
        }

        return new Response("join", "회원가입 성공");
    }

    @PostMapping(value = "/join/profile", consumes = {"multipart/form-data"})
    public String uploadProfile(@RequestParam("img") MultipartFile file) {
        String url = "";
        url = uploadFileService.uploadImgFile(file);
        System.out.println("url:" + url);
        return url;
    }

    // 타인 정보 열람
    @GetMapping("/{name}")
    public ModelAndView showUserDetail(@PathVariable String name, @CookieValue(value = "accessToken", required = false) String accessToken) {
        ModelAndView view = new ModelAndView("user_detail");

        // 타인 정보
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByName(name));
        User user = optionalUser.orElse(null);

        if (user == null) {
            return new ModelAndView(new RedirectView("/"));
        }
        String id = user.getId();

        UserResponseDto userResponseDto = new UserResponseDto(user);
        view.addObject("user", userResponseDto);
        List<String> needLang = languageService.getNeedLanguage(id);
        List<String> useLang = languageService.getUseLanguage(id);
        view.addObject("needLang", needLang);
        view.addObject("useLang", useLang);

        // 내 정보
        if (accessToken != null) {
            Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
            String myId = claims.get("id", String.class);

            userRepository.findById(myId).ifPresent(myUser -> view.addObject("myUser", myUser));


            // 해당 유저에 대한 즐겨찾기 or 차단 여부 추가
            String userId = user.getId();
            if (likeService.isUserLikeExists(myId, userId)) {
                view.addObject("isLiked", true);
            } else if (blockService.isUserBlockExists(myId, userId)) {
                view.addObject("isBlocked", true);
            }
        }

        return view;
    }

    //    @ResponseBody
    @PostMapping("/join/emailCheck")
    public BaseResponse<String> emailCheck(@RequestBody EmailCheckReq emailCheckReq) throws MessagingException, UnsupportedEncodingException {
        String authCode = emailService.sendEmail(emailCheckReq.getEmail());
        return new BaseResponse<>(authCode);
    }

    @PostMapping("/join/idCheck")
    public Response idCheck(@RequestBody UserRequestDto userRequestDto) {
        System.out.println(userRequestDto);
        String id = userRequestDto.getId();
        Optional<User> optionalMyUser = userRepository.findById(id);
        User myUser = optionalMyUser.orElse(null);

        if (myUser != null) {
            return new Response("fail", id); // 예시: 이미 사용 중인 아이디
        }
        return new Response("success", id); // 예시: 사용 가능한 아이디
    }

    @PostMapping("/join/nameCheck")
    public Response nameCheck(@RequestBody UserRequestDto userRequestDto) {
        System.out.println(userRequestDto);
        String name = userRequestDto.getName();
        Optional<User> optionalMyUser = Optional.ofNullable(userRepository.findByName(name));
        User myUser = optionalMyUser.orElse(null);

        if (myUser != null) {
            return new Response("fail", name); // 예시: 이미 사용 중인 아이디
        }
        return new Response("success", name); // 예시: 사용 가능한 아이디
    }


    /**
     * 유저 10인 정보 불러오기(프로필 게시판)
     **/
    @GetMapping("list/{page}")
    public ModelAndView userList(@PathVariable int page,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) String nation,
                                 @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        int pageSize = 10; // 한 페이지에 보여질 아이템 수


        ModelAndView view = new ModelAndView("user_list");
        Page<User> getUserList = null;

        if (name != null && !name.isEmpty()) { // 이름 검색
            getUserList = userRepository.findByNameContaining(name, pageable.withPage(page - 1));
        } else if (nation != null && !nation.isEmpty()) {
            getUserList = userRepository.findByLiveCountry(nation, pageable.withPage(page - 1));
        } else {
            getUserList = userRepository.findAll(pageable.withPage(page - 1));
            System.out.println(getUserList);
        }
        view.addObject("userList", getUserList);

        int totalPages = getUserList.getTotalPages();
        int currentPageGroup = (page - 1) / pageSize;
        int startPage = currentPageGroup * pageSize + 1;
        int endPage = Math.min(startPage + pageSize - 1, totalPages);

        view.addObject("startPage", startPage);
        view.addObject("endPage", endPage);

        return view;
    }

    /**
     * 회원 탈퇴
     **/
    @DeleteMapping("resign")
    public Response delete(@RequestBody UserRequestDto userRequestDto, HttpServletResponse responselogout) {
        String id = userRequestDto.getId();
        String password = userRequestDto.getPassword();

        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElse(null);

        if (user != null && !user.getPassword().equals(password)) {
            return new Response("delete", "fail");
        }

        try {
            userService.deleteUser(id);
            logout(responselogout);
            return new Response("delete", "success");
        } catch (Exception e) {
            return new Response("delete", "fail");
        }
    }

    /* 마이페이지 */
    @GetMapping("mypage")
    public ModelAndView myPageMain(@CookieValue(value = "accessToken", required = false) String accessToken) {
        ModelAndView view = new ModelAndView("user_my_page");
        if (accessToken == null) {
            return new ModelAndView(new RedirectView("login"));
        } else {
            Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
            String id = claims.get("id", String.class);
            User user = userRepository.findById(id).orElse(null);
            if (user != null) {
                view.addObject(user);
            }
        }
        return view;
    }

    // 마이페이지 메뉴 내용 출력
    @GetMapping("mypage/content")
    public ModelAndView myPageUpdate(@RequestParam String menu, @CookieValue(value = "accessToken", required = false) String accessToken) {
        ModelAndView view = new ModelAndView();

        if (accessToken == null) {
            return view;
        }

        List<Language> languages = languageRepository.findAll();
        // DB에 저장되어있는 언어코드 목록 가져오기
        if (!languages.isEmpty()) {
            List<String> languageCode = new ArrayList<>();
            for (Language language : languages) {
                languageCode.add(language.getLanguageCode());
            }
            view.addObject("languageCode", languageCode);
        }

        Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
        String id = claims.get("id", String.class);
        User user = userRepository.findById(id).orElse(null);


        // 유저 정보 add
        view.addObject("user", user);

        switch (menu) {
            case "update": // 정보 조회/수정
                List<String> needLang = languageService.getNeedLanguage(id);
                List<String> useLang = languageService.getUseLanguage(id);

                view.setViewName("mypage_update");
                view.addObject("needLang", needLang);
                view.addObject("useLang", useLang);
                break;
            case "like-block": // 즐겨찾기/차단 목록
                // 해당 유저의 like 리스트 <- name으로
                List<String> likeIds = new ArrayList<>();
                likeIds = likeService.likeList(id);
                List<String> likeList = new ArrayList<>();
                if (!likeIds.isEmpty()) {
                    for (String likeId : likeIds) {
                        String name = userService.getUserName(likeId);
                        likeList.add(name);
                    }
                }
                view.addObject("likeList", likeList);
                // 해당 유저의 block 리스트 <- name으로
                List<String> blockIds = new ArrayList<>();
                blockIds = blockService.blockList(id);
                List<String> blockList = new ArrayList<>();
                if (!blockIds.isEmpty()) {
                    for (String likeId : blockIds) {
                        String name = userService.getUserName(likeId);
                        blockList.add(name);
                    }
                }
                view.addObject("blockList", blockList);
                view.setViewName("mypage_like_block");
                break;
            case "board-free":
                // 해당 유저의 board_free 작성글 리스트 추가
                List<BoardFree> boardFreeList = boardFreeService.getBoardListById(id);
                view.addObject("boardList", boardFreeList);
                view.setViewName("mypage_board_free");
                break;
            case "board-community":
                // 해당 유저의 board_community 작성글 리스트 추가
                List<BoardCommunity> boardCommunityList = boardCommunityService.getBoardListById(id);
                view.addObject("boardList", boardCommunityList);
                // 해당 글에서 신청자 추가
                if (!boardCommunityList.isEmpty()) {
                    // boardNo, 참가자
                    Map<Integer, List<User>> participantUsers = new HashMap<>();
                    for (BoardCommunity board : boardCommunityList) {
                        int boardNo = board.getBoardNo();
                        // 신청한 참가자 목록
                        List<Participant> participants = participantService.getListByBoardNoAndAccept(boardNo, 0); // 신청자 : 0
                        // 해당 참가자 목록으로 user 객체 리스트로 바꾸기
                        List<User> users = new ArrayList<>();
                        for (Participant pUser : participants) {
                            User usr = userRepository.findById(pUser.getParticipantId()).orElse(null);
                            if (user != null) {
                                users.add(usr);
                            }
                        }
                        participantUsers.put(boardNo, users);
                    }
                    view.addObject("participantUsers", participantUsers);
                }
                view.setViewName("mypage_board_community");
                break;
            case "community-accept":
                // 내가 신청을 넣었던 모집 게시판들 모음
                List<Participant> participants = participantService.getListAllByUserId(id);
                view.addObject("participants", participants);
                if (!participants.isEmpty()) {
                    Map<Integer, BoardCommunity> boardList = new HashMap<>();
                    for (Participant part : participants) {
                        int boardNo = part.getBoardNo();
                        BoardCommunity board = boardCommunityService.getBoard(boardNo);
                        boardList.put(boardNo, board);
                    }
                    view.addObject("boardList", boardList);
                }
                view.setViewName("mypage_community_accept");
                break;
            case "resign":
                view.setViewName("mypage_resign");
                break;
        }

        return view;
    }

    @PutMapping("update")
    public Response update(@RequestBody UserRequestDto userRequestDto) {

        String id = userRequestDto.getId();
        List<String> needLang = userRequestDto.getNeedLang();
        List<String> useLang = userRequestDto.getUseLang();
        String profileImg = userRequestDto.getProfileImg();

        User user = userRepository.findById(id).orElse(null);
        try {
            userService.updateUser(id, userRequestDto, profileImg);
            // 정상적으로 유저가 DB에 등록되면 useLang과 needLang도 등록
            languageService.setUseLanguage(id, useLang);
            languageService.setNeedLang(id, needLang);
        } catch (Exception e) {
            return new Response("err", "회원수정 실패");
        }

        return new Response("update", "회원수정 성공");
    }

//
//    @PutMapping("ExampleCode")
//    public ModelAndView ExampleCode
//            (@CookieValue(value = "accessToken", required = false) String accessToken) {
//        ModelAndView view = new ModelAndView();
//
//        Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
//        String id = claims.get("id", String.class);
//        User user = userRepository.findById(id).orElse(null);
//        if (user != null) {
//            view.addObject(user);
//        }
//        return view;
//    }
}