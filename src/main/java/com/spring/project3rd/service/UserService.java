package com.spring.project3rd.service;

import com.spring.project3rd.domain.user.User;
import com.spring.project3rd.domain.user.UserRepository;
import com.spring.project3rd.domain.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

}
