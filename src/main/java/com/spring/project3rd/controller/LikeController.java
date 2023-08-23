package com.spring.project3rd.controller;


import com.spring.project3rd.domain.like.Like;
import com.spring.project3rd.domain.like.LikeId;
import com.spring.project3rd.domain.like.LikeRepository;
import com.spring.project3rd.security.jwt.util.JwtTokenizer;
import com.spring.project3rd.service.LikeService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/like")
public class LikeController {
    private final LikeRepository likeRepository;
    private final LikeService likeService;
    private final JwtTokenizer jwtTokenizer;

    // 즐겨찾기
    @PostMapping("/")
    public ResponseEntity<String> likeUser(@RequestBody LikeId ids){
        String userID = ids.getUserId();
        String likeId = ids.getLikeId();
        Like like = new Like(userID,likeId);
        try{
            likeRepository.save(like);
            return ResponseEntity.status(HttpStatus.OK).body("Like saved successfully.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error save like.");
        }
    }

    // 즐겨찾기 취소
    @DeleteMapping("/cancel")
    public ResponseEntity<String> likeCancelUser(@RequestBody LikeId ids){
        String userID = ids.getUserId();
        String likeId = ids.getLikeId();
        Like like = new Like(userID,likeId);
        try{
            likeService.likeCancel(like);
            return ResponseEntity.status(HttpStatus.OK).body("Like canceled successfully.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error cancel like.");
        }
    }

    // 내가 즐겨찾기한 유저 목록
    @GetMapping("/list")
    public List<String> showLikeList(@CookieValue(value="accessToken", required = false) String accessToken){
        List<String> likedIds = new ArrayList<>();
        if(accessToken!=null){
            Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
            String id = claims.get("id", String.class);
            likedIds = likeService.likeList(id);
        }
        return likedIds;
    }
}
