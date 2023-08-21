package com.spring.project3rd.service;

import com.spring.project3rd.domain.user.User;
import com.spring.project3rd.domain.user.UserRepository;
import com.spring.project3rd.domain.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void updateUser(String id, String name, UserRequestDto userRequestDto, String url) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자")
        );
        user.update(userRequestDto, url);
    }
    @Transactional
    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

    // 비활성화된 유저 아이디 목록
    public List<String> inactiveUserIds(){
        List<String> list = userRepository.findInactiveUserIds();
        // 찾는 아이디가 없을 경우 null이 아닌 빈 문자열을 리턴하므로
        // 리스트 확인 시 isEmpty() 이용 필요
        return list;
    }

    // 유저 id를 받아 해당 유저의 name 리턴
    public String getUserName(String id){
        String name = "";
        User user = userRepository.findById(id).orElse(null);
        if(user!=null){
            name=user.getName();
        }
        return name;
    }

    // 유저의 name을 받아 해당 유저의 id를 리턴
    public String getUserIdByName(String name){
        String id = "";
        User user = userRepository.findByName(name);
        if(user!=null){
            id=user.getId();
        }
        return id;
    }

}

