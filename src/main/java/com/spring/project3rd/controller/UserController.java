package com.spring.project3rd.controller;

import com.spring.project3rd.domain.user.UserRepository;
import com.spring.project3rd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;



}
