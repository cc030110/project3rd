package com.spring.project3rd.service;

import com.spring.project3rd.domain.Like.Like;
import com.spring.project3rd.domain.Like.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    @Transactional
    public void likeCancel(Like like){
        likeRepository.delete(like);
    }

    // 즐겨찾기한 유저 리스트
    public List<String> likeList(String userId){
        List<String> list = likeRepository.findLikeIdsByUserId(userId);
        return list;
    }

}
