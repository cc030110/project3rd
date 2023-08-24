package com.spring.project3rd.service;

import com.spring.project3rd.domain.userBlock.UserBlock;
import com.spring.project3rd.domain.userBlock.UserBlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlockService {

    private final UserBlockRepository userBlockRepository;

    @Transactional
    public void unblock(UserBlock block){
        userBlockRepository.delete(block);
    }

    // 차단한 유저 리스트
    public List<String> blockList(String id){
        List<String> blockedIds = userBlockRepository.findBlockIdsByUserId(id);
        return blockedIds;
    }

}
