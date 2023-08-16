
package com.spring.project3rd.controller;

import com.spring.project3rd.domain.user.*;
//import com.spring.project3rd.service.UserService;
import com.spring.project3rd.security.jwt.util.JwtTokenizer;
import com.spring.project3rd.service.UploadFileService;
import com.spring.project3rd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
@SessionAttributes({"log"})
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UploadFileService uploadFileService;
    private final JwtTokenizer jwtTokenizer;

//    @PostMapping("login")
//    public ResponseEntity<JwtToken> loginSuccess(@RequestBody Map<String, String> loginForm){
//        JwtToken token = userService.login(loginForm.get("id"), loginForm.get("password"));
//        return ResponseEntity.ok(token);
//    }

    /*@SessionScope
    @PostMapping("login")
    public ModelAndView login(@RequestBody User user) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("log", user.getId());
        return modelAndView;
    }*/

//    @SessionScope
//    @PostMapping("login")
//    public ResponseEntity<String> login(WebRequest request, @RequestBody User requestUser) {
//        String resultMsg = "";
//
//        Optional<User> optionalUser = userRepository.findById(requestUser.getId());
//        // 해당 값이 없으면 null
//        User user = optionalUser.orElse(null);
//
//        if(user!=null&&user.getPassword().equals(requestUser.getPassword())){
//            request.setAttribute("log",user.getId(), WebRequest.SCOPE_SESSION);
//            resultMsg = "success";
//        }else{
//            resultMsg = "fail";
//        }
//        return ResponseEntity.ok(resultMsg);
//    }
    @PostMapping("login")
    public ResponseEntity login(@RequestBody @Valid MemberLoginDto loginDto) {

        // TODO email에 해당하는 사용자 정보를 읽어와서 암호가 맞는지 검사하는 코드가 있어야 한다.
        String id = loginDto.getId();
        String password = loginDto.getPassword();
        System.out.println(id);
        System.out.println(password);

        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElse(null);

        if(user!=null&&user.getPassword().equals(loginDto.getPassword())){
            List<String> roles = List.of("ROLE_USER");
            String name = user.getName();
            String accessToken = jwtTokenizer.createAccessToken(id, name);
            String refreshToken = jwtTokenizer.createRefreshToken(id, name);

            MemberLoginResponseDto loginResponse = MemberLoginResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken); // 헤더에 토큰 추가

            System.out.println(accessToken);
            System.out.println(refreshToken);

            return ResponseEntity.ok().headers(headers).body(loginResponse);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /*
    @PostMapping("logout")
    public String logout(WebRequest request, SessionStatus status){
        // 우선 호출 후,
        status.setComplete();
        // 세션 속성을 수정
        request.removeAttribute("log", WebRequest.SCOPE_SESSION);
        return "redirect:/";
    }

    /** 회원 가입 **/
    @PostMapping(value = "join", consumes = {"multipart/form-data"})
    public Map join(@ModelAttribute UserRequestDto userRequestDto){
        JSONObject response = new JSONObject();
        String url = uploadFileService.uploadImgFile(userRequestDto.getProfileImg());

        try {
            User user = userRepository.findById(userRequestDto.getId()).orElseThrow(
                    () -> new IllegalArgumentException("ID 중복 확인")
            );
            response.put("join", "fail");
        } catch (Exception e) {
            User newUser = new User(userRequestDto, url);
            userRepository.save(newUser);
            response.put("join", "success");
        }
        return response.toMap();
    }

    /** 유저 1인 정보 불러오기(회원 수정에 쓸거)**/
    @GetMapping("{id}")
    public ModelAndView showUser(@PathVariable String id){
        ModelAndView view = new ModelAndView("user_myPage");

        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElse(null);

        view.addObject("user",user);

        return view;
    }

    /**유저 10인 정보 불러오기(프로필 게시판)**/
    @GetMapping("list/{pageNumber}")
    public List<User> getUserAll(@PathVariable() int pageNumber, @RequestParam(required = false) String keyword, @PageableDefault(size = 10) Pageable pageable){
        if(keyword != null && !keyword.equals("")) {
            String pattern = "%" + keyword + "%";
            return userRepository.findAllByIdLike(pattern, pageable.withPage(pageNumber-1));
        } else {
            return userRepository.findAll(pageable.withPage(pageNumber-1)).getContent();
        }
    }

    /** 회원 정보 수정 **/
    @PutMapping("update")
    public ModelAndView showUserinfo(WebRequest webRequest){

        ModelAndView view = new ModelAndView();
        String log = (String) webRequest.getAttribute("log",WebRequest.SCOPE_SESSION);
        // 로그인 상태 확인
        if(log!=null){ // 로그인 중
            Optional<User> optionalUser = userRepository.findById(log);
            User user = optionalUser.orElse(null);
            if(user!=null){
                view.setViewName("user_update");
                view.addObject("user",user);
            }else{
                view.setViewName("login");
            }
        }else{
            view.setViewName("login");
        }

        return view;
    }


    @PutMapping(value = "{id}/update", consumes = {"multipart/form-data"})
    public Map update(WebRequest request, @PathVariable String id, @ModelAttribute UserRequestDto userRequestDto){
        JSONObject response = new JSONObject();
        String url = uploadFileService.uploadImgFile(userRequestDto.getProfileImg());
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);

        if(log != null){
            return null;
        }
        // 로그인 처리 후 수정할것
        userRequestDto.setId(log);
        userService.updateUser(id, log, userRequestDto, url);
        response.put("user", "update");
        return response.toMap();
    }

    /** 회원 탈퇴 **/
    @DeleteMapping("{id}/delete")
    public Map delete(WebRequest request, @PathVariable String id, UserRequestDto userRequestDto){
        JSONObject response = new JSONObject();

        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저")
        );
        System.out.println(log);
        if(log != null )
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


