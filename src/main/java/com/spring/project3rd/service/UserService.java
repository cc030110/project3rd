package com.spring.project3rd.service;

import com.spring.project3rd.domain.user.User;
import com.spring.project3rd.domain.user.UserRepository;
import com.spring.project3rd.domain.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void updateUser(String id, String log, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자")
        );
        user.update(userRequestDto);
    }
    @Transactional
    public void deleteUser(String id){
        userRepository.deleteById(id);

    }


}

