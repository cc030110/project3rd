package com.spring.project3rd.controller;

import com.spring.project3rd.config.jwt.JwtToken;
import com.spring.project3rd.domain.user.User;
import com.spring.project3rd.domain.user.UserRepository;
import com.spring.project3rd.domain.user.UserRequestDto;
import com.spring.project3rd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/user")
// 세션 로그인
@Controller
@SessionAttributes({"log"})
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

//    @PostMapping("login")
//    public ResponseEntity<JwtToken> loginSuccess(@RequestBody Map<String, String> loginForm){
//        JwtToken token = userService.login(loginForm.get("id"), loginForm.get("password"));
//        return ResponseEntity.ok(token);
//    }

    @SessionScope
    @PostMapping("login")
    public ModelAndView login(@RequestBody User user) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("log", user.getId());
        return modelAndView;
    }

    @PostMapping("logout")
    public String logout(WebRequest request, SessionStatus status){
        // 우선 호출 후,
        status.setComplete();
        // 세션 속성을 수정
        request.removeAttribute("log", WebRequest.SCOPE_SESSION);
        return "redirect:/";
    }

    // 유저 1인 정보 불러오기(회원 수정에 쓸거)
    @GetMapping("{id}")
    public User getUserById(@PathVariable String id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저")
        );
        return user;
    }

    // 회원 가입
    @PostMapping(value = "join", consumes = {"multipart/form-data"})
    public Map join(@ModelAttribute UserRequestDto userRequestDto){
        JSONObject response = new JSONObject();
        try {
            User user = userRepository.findById(userRequestDto.getId()).orElseThrow(
                    () -> new IllegalArgumentException("ID 중복 확인")
            );
            user = userRepository.findById(userRequestDto.getId()).orElseThrow(
                    () -> new IllegalArgumentException("ID 중복 확인")
            );
            user = userRepository.findById(userRequestDto.getId()).orElseThrow(
                    () -> new IllegalArgumentException("ID 중복 확인")
            );
            response.put("join", "fail");
        } catch (Exception e) {
            User newUser = new User(userRequestDto);
            userRepository.save(newUser);
            response.put("join", "success");
        }
        return response.toMap();
    }

    // 회원 정보 수정
//    @PutMapping(value = "{id/update}", consumes = {"multipart/form-data"})
//    public Map update(WebRequest request, @PathVariable String id, @ModelAttribute UserRequestDto userRequestDto){
//        JSONObject response = new JSONObject();
//        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
//
//        if(log != null){
//            response.put("user", "update");
//            return null;
//        }
//        // 로그인 처리 후 수정할것
//
//        return response.toMap();
//    }



}

