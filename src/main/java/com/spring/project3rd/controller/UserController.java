
package com.spring.project3rd.controller;

import com.spring.project3rd.domain.user.User;
import com.spring.project3rd.domain.user.UserRepository;
import com.spring.project3rd.domain.user.UserRequestDto;
//import com.spring.project3rd.service.UserService;
import com.spring.project3rd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/user")
@SessionAttributes({"log"})
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

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

    @SessionScope
    @PostMapping("login")
    public ResponseEntity<String> login(WebRequest request, @RequestBody User requestUser) {
        String resultMsg = "";

        Optional<User> optionalUser = userRepository.findById(requestUser.getId());
        // 해당 값이 없으면 null
        User user = optionalUser.orElse(null);

        if(user!=null&&user.getPassword().equals(requestUser.getPassword())){
            request.setAttribute("log",user.getId(), WebRequest.SCOPE_SESSION);
            resultMsg = "success";
        }else{
            resultMsg = "fail";
        }
        return ResponseEntity.ok(resultMsg);
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
        try {
            User user = userRepository.findById(userRequestDto.getId()).orElseThrow(
                    () -> new IllegalArgumentException("ID 중복 확인")
            );
//            user = userRepository.findById(userRequestDto.getId()).orElseThrow(
//                    () -> new IllegalArgumentException("ID 중복 확인")
//            );
//            user = userRepository.findById(userRequestDto.getId()).orElseThrow(
//                    () -> new IllegalArgumentException("ID 중복 확인")
//            );
            response.put("join", "fail");
        } catch (Exception e) {
            User newUser = new User(userRequestDto);
            System.out.println(newUser.getGender());
            userRepository.save(newUser);
            response.put("join", "success");
        }
        return response.toMap();
    }

    /** 유저 1인 정보 불러오기(회원 수정에 쓸거)**/
    @GetMapping("{id}")
    public User getUserById(@PathVariable String id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저")
        );
        return user;
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
    @PutMapping(value = "{id}/update", consumes = {"multipart/form-data"})
    public Map update(WebRequest request, @PathVariable String id, @ModelAttribute UserRequestDto userRequestDto){
        JSONObject response = new JSONObject();
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);

        if(log != null){
            return null;
        }
        // 로그인 처리 후 수정할것
        userRequestDto.setId(log);
        userService.updateUser(id, log, userRequestDto);
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


