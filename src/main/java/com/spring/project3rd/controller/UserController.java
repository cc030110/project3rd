package com.spring.project3rd.controller;

import com.spring.project3rd.config.jwt.JwtToken;
import com.spring.project3rd.domain.user.UserRepository;
import com.spring.project3rd.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
//    private final UserRepository userRepository;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtToken> loginSuccess(@RequestBody Map<String, String> loginForm){
        JwtToken token = userService.login(loginForm.get("username"), loginForm.get("password"));
        return ResponseEntity.ok(token);
    }

}
