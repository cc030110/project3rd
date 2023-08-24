package com.spring.project3rd.service;

import com.spring.project3rd.domain.userLike.UserLike;
import com.spring.project3rd.domain.userLike.UserLikeId;
import com.spring.project3rd.domain.userLike.UserLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final UserLikeRepository likeRepository;

    @Transactional
    public void likeCancel(UserLike like){
        likeRepository.delete(like);
    }

    // 즐겨찾기한 유저 리스트
    public List<String> likeList(String userId){
        return likeRepository.findLikeIdsByUserId(userId);
    }

    // 즐겨찾기 여부 확인
    public boolean isUserLikeExists(String userId, String likeId) {
        UserLikeId userLikeId = new UserLikeId(userId, likeId);
        Optional<UserLike> userLike = likeRepository.findById(userLikeId);
        return userLike.isPresent();
    }
}
