
package com.spring.project3rd.controller;

import com.spring.project3rd.domain.email.BaseResponse;
import com.spring.project3rd.domain.email.EmailCheckReq;
import com.spring.project3rd.domain.user.EmailService;
import com.spring.project3rd.domain.language.*;
import com.spring.project3rd.domain.user.*;
import com.spring.project3rd.payload.Response;
import com.spring.project3rd.security.jwt.util.JwtTokenizer;
import com.spring.project3rd.service.LanguageService;
import com.spring.project3rd.service.UploadFileService;
import com.spring.project3rd.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

@RequiredArgsConstructor
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
    @PostMapping("login")
    public ResponseEntity login(@RequestBody @Valid MemberLoginDto loginDto, HttpServletResponse response) {

        // TODO email에 해당하는 사용자 정보를 읽어와서 암호가 맞는지 검사하는 코드가 있어야 한다.
        String id = loginDto.getId();
        String password = loginDto.getPassword();
        System.out.println(id);
        System.out.println(password);

        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElse(null);

        if(user!=null&&user.getPassword().equals(loginDto.getPassword())){
            String name = user.getName();
            String accessToken = jwtTokenizer.createAccessToken(id, name);
            String refreshToken = jwtTokenizer.createRefreshToken(id, name);

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
            return new Response("join-err-id","중복되는 아이디 사용 불가");
        }
        // 이메일 중복 검사
        String email = userRequestDto.getEmail();
        user = userRepository.findByEmail(email);
        if(user!=null){
            return new Response("join-err-email","중복되는 이메일 사용 불가");
        }
        // 이름(닉네임) 중복 검사
        String name = userRequestDto.getName();
        user = userRepository.findByName(name);
        if(user!=null){
            return new Response("join-err-name","중복되는 닉네임 사용 불가");
        }

        User joinUser = new User(userRequestDto);
        try{
            userRepository.save(joinUser);
            // 정상적으로 유저가 DB에 등록되면 useLang과 needLang도 등록
            languageService.setUseLanguage(id,useLang);
            languageService.setNeedLang(id,needLang);
        }catch (Exception e){
            return new Response("join-err","회원가입 실패");
        }

        return new Response("join-success","회원가입 성공");
    }

    //
    @PostMapping(value="/join/profile", consumes = {"multipart/form-data"})
    public String uploadProfile(@RequestParam("img") MultipartFile file){
        String url = "";
        url = uploadFileService.uploadImgFile(file);
        System.out.println("url:"+url);
        return url;
    }

    /** 유저 1인 정보 불러오기(회원 수정에 쓸거)**/
    @GetMapping("{id}")
    public ModelAndView showUser(@CookieValue(value = "accessToken", required = false) String accessToken){
        ModelAndView view = new ModelAndView("user_myPage");

        Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
        String id = claims.get("id", String.class);
        String name = claims.get("name", String.class);
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElse(null);

        if(user!=null){
            UserResponseDto userResponseDto = new UserResponseDto(user);
            view.addObject("user",userResponseDto);

            return view;
        }
        return null;
    }

    @ResponseBody
    @PostMapping("/join/emailCheck")
    public BaseResponse<String> emailCheck(@RequestBody EmailCheckReq emailCheckReq) throws MessagingException, UnsupportedEncodingException {
        String authCode = emailService.sendEmail(emailCheckReq.getEmail());
        return new BaseResponse<>(authCode);
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




    /** 회원 정보 수정 **/
//    @PutMapping(value = "update", consumes = {"multipart/form-data"})
//    public Map update(@CookieValue(value = "accessToken", required = false) String accessToken, @ModelAttribute UserRequestDto userRequestDto){
//        JSONObject response = new JSONObject();
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
    public Map delete(@CookieValue(value = "accessToken", required = false) String accessToken, UserRequestDto userRequestDto){
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
            response.put("delete", "success");
        } else {
            response.put("delete", "fail");
        }

        return response.toMap();
    }

}