
package com.spring.project3rd.controller;

import com.spring.project3rd.domain.email.BaseResponse;
import com.spring.project3rd.domain.email.EmailCheckReq;
import com.spring.project3rd.security.jwt.util.RefreshToken;
import com.spring.project3rd.security.jwt.util.RefreshTokenRepository;
import com.spring.project3rd.service.EmailService;
import com.spring.project3rd.domain.language.*;
import com.spring.project3rd.domain.user.*;
import com.spring.project3rd.payload.Response;
import com.spring.project3rd.security.jwt.util.JwtTokenizer;
import com.spring.project3rd.service.LanguageService;
import com.spring.project3rd.service.UploadFileService;
import com.spring.project3rd.service.UserService;
import io.jsonwebtoken.Claims;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
    private final RefreshTokenRepository refreshTokenRepository;
    @Autowired
    public UserController(UserService userService, UserRepository userRepository,
                          UploadFileService uploadFileService, JwtTokenizer jwtTokenizer,
                          LanguageRepository languageRepository, LanguageService languageService,
                          EmailService emailService, RefreshTokenRepository refreshTokenRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.uploadFileService = uploadFileService;
        this.jwtTokenizer = jwtTokenizer;
        this.languageRepository = languageRepository;
        this.languageService = languageService;
        this.emailService = emailService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody @Valid MemberLoginDto loginDto, HttpServletResponse response) {

        String id = loginDto.getId();
        String password = loginDto.getPassword();

        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElse(null);

        if(user!=null&&user.getPassword().equals(loginDto.getPassword())){
            String name = user.getName();
            String accessToken = jwtTokenizer.createAccessToken(id, name);
            String refreshToken = JwtTokenizer.createRefreshToken(id);
            // 레디스 저장
            refreshTokenRepository.save(new RefreshToken(String.valueOf(loginDto.getId()), refreshToken, accessToken));

            MemberLoginResponseDto loginResponse = MemberLoginResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
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



    /** 회원 가입 **/
    @GetMapping("/join")
    public ModelAndView JoinForm() {
        ModelAndView view = new ModelAndView("user_join");
        List<Language> languages = languageRepository.findAll();
        // DB에 저장되어있는 언어코드 목록 가져오기
        if(!languages.isEmpty()){
            List<String> languageCode = new ArrayList<>();
            for (Language language : languages) {
                languageCode.add(language.getLanguageCode());
            }
            view.addObject("languageCode",languageCode);
        }
        return view;
    }
    @PostMapping(value = "/join")
    public Response join(@RequestBody UserRequestDto userRequestDto){
        List<String> needLang = userRequestDto.getNeedLang();
        List<String> useLang = userRequestDto.getUseLang();

        // 아이디 중복 검사
        String id = userRequestDto.getId();
        User user = userRepository.findById(id).orElse(null);
        if(user!=null){
            return new Response("err","중복되는 아이디 사용 불가");
        }
        // 이메일 중복 검사
        String email = userRequestDto.getEmail();
        user = userRepository.findByEmail(email);
        if(user!=null){
            return new Response("err","중복되는 이메일 사용 불가");
        }
        // 이름(닉네임) 중복 검사
        String name = userRequestDto.getName();
        user = userRepository.findByName(name);
        if(user!=null){
            return new Response("err","중복되는 닉네임 사용 불가");
        }

        User joinUser = new User(userRequestDto);
        try{
            userRepository.save(joinUser);
            // 정상적으로 유저가 DB에 등록되면 useLang과 needLang도 등록
            languageService.setUseLanguage(id,useLang);
            languageService.setNeedLang(id,needLang);
        }catch (Exception e){
            return new Response("err","회원가입 실패");
        }

        return new Response("join","회원가입 성공");
    }

    //
    @PostMapping(value="/join/profile", consumes = {"multipart/form-data"})
    public String uploadProfile(@RequestParam("img") MultipartFile file){
        String url = "";
        url = uploadFileService.uploadImgFile(file);
        System.out.println("url:"+url);
        return url;
    }

    // 타인 정보 열람
    @GetMapping("/{name}")
    public ModelAndView showUserDetail(@PathVariable String name, @CookieValue(value = "accessToken", required = false) String accessToken) {
        ModelAndView view = new ModelAndView("user_detail");

        // 타인 정보 뷰
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByName(name));
        User user = optionalUser.orElse(null);

        if (user != null) {
            UserResponseDto userResponseDto = new UserResponseDto(user);
            view.addObject("user", userResponseDto);
        } else {
            view.setViewName("error_page");
            return view;
        }

        // 내 정보 뷰
        if (accessToken != null) {
            Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
            String myId = claims.get("id", String.class);
            String myName = claims.get("name", String.class);
            Optional<User> optionalMyUser = userRepository.findById(myId);
            User myUser = optionalMyUser.orElse(null);

            if (myUser != null) {
                UserResponseDto myUserResponseDto = new UserResponseDto(myUser);
                view.addObject("myUser", myUserResponseDto);
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
    public ResponseEntity<String> idCheck(@RequestBody MemberLoginDto loginDto) {

        String id = loginDto.getId();
        Optional<User> optionalMyUser = userRepository.findById(id);
        User myUser = optionalMyUser.orElse(null);

        if (myUser != null) {
            return ResponseEntity.badRequest().body("ID already exists"); // 예시: 이미 사용 중인 아이디
        } else {
            return ResponseEntity.ok("ID available"); // 예시: 사용 가능한 아이디
        }
    }


    /**유저 10인 정보 불러오기(프로필 게시판)**/
    @GetMapping("list/{page}")
    public ModelAndView userList(@PathVariable int page,
                                 @RequestParam(defaultValue = "") String keyword,
                                 Pageable pageable,
                                 @CookieValue(value = "accessToken", required = false) String accessToken){

        int pageSize = 5; // 한 페이지에 보여질 아이템 수를 1로 설정
        pageable = PageRequest.of(page - 1, pageSize); // 페이지 정보 재설정

        ModelAndView view = new ModelAndView("user_list");
        Page<User> getUserList = null;

        getUserList = userRepository.findAllByOrderByCreatedAtDesc(pageable);

        int totalPages = getUserList.getTotalPages();
        int currentPageGroup = (page - 1) / 5;
        int startPage = currentPageGroup * 5 + 1;
        int endPage = Math.min(startPage + 4, totalPages);

        view.addObject("startPage", startPage);
        view.addObject("endPage", endPage);
        view.addObject("userList", getUserList);

        return view;
    }

    @GetMapping("/mypage")
    public String myPage() {
        return "user_my_page";
    }

    /** 개인 정보 불러오기(회원 수정에 쓸거)**/
//    @GetMapping("update")
//    public ModelAndView showUser(@CookieValue(value = "accessToken", required = false) String accessToken){
//        ModelAndView view = new ModelAndView("user_my_page");
//
//        Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
//        String id = claims.get("id", String.class);
////        String name = claims.get("name", String.class);
//        Optional<User> optionalUser = userRepository.findById(id);
//        User user = optionalUser.orElse(null);
//
//        if(user!=null){
//            UserResponseDto userResponseDto = new UserResponseDto(user);
//            view.addObject("user",userResponseDto);
//
//            return view;
//        }
//        return null;
//    }


    /** 회원 정보 수정 **/
//    @PutMapping(value = "update", consumes = {"multipart/form-data"})
//    public Map update(@CookieValue(value = "accessToken", required = false) String accessToken, @ModelAttribute UserRequestDto userRequestDto){
//
//        JSONObject response = new JSONObject();
//
//        String url = uploadFileService.uploadImgFile(userRequestDto.getProfileImg());
//        Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
//        String id = claims.get("id", String.class);
//        String name = claims.get("name", String.class);
//
//        if(id != null){
//            Optional<User> optionalUser = userRepository.findById(id);
//            User user = optionalUser.orElse(null);
//            userRequestDto.setId(id);
//            userService.updateUser(id, name, userRequestDto, url);
//            response.put("user", "update");
//            return response.toMap();
//        }
//        return null;
//    }


    /** 회원 탈퇴 **/
    @DeleteMapping("/delete")
    public Map delete(@CookieValue(value = "accessToken", required = false) String accessToken, UserRequestDto userRequestDto, HttpServletResponse responselogout){
        JSONObject response = new JSONObject();

        Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
        String id = claims.get("id", String.class);
        String name = claims.get("name", String.class);

        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElse(null);

        if(id.equals(user.getId()))
        //&& log.equals(user.getId())
        {
            userService.deleteUser(id);
            logout(responselogout);
            response.put("delete", "success");
        } else {
            response.put("delete", "fail");
        }

        return response.toMap();
    }
}