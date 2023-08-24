package com.spring.project3rd.service;

import com.spring.project3rd.domain.userBlock.UserBlock;
import com.spring.project3rd.domain.userBlock.UserBlockId;
import com.spring.project3rd.domain.userBlock.UserBlockRepository;
import com.spring.project3rd.domain.userLike.UserLike;
import com.spring.project3rd.domain.userLike.UserLikeId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        return userBlockRepository.findBlockIdsByUserId(id);
    }

    // 차단 여부 확인
    public boolean isUserBlockExists(String userId, String likeId) {
        UserBlockId userBlockId = new UserBlockId(userId, likeId);
        Optional<UserBlock> userLike = userBlockRepository.findById(userBlockId);
        return userLike.isPresent();
    }

}
