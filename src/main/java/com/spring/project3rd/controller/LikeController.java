package com.spring.project3rd.controller;

import com.spring.project3rd.domain.userLike.UserLike;
import com.spring.project3rd.domain.userLike.UserLikeId;
import com.spring.project3rd.domain.userLike.UserLikeRepository;
import com.spring.project3rd.payload.Response;
import com.spring.project3rd.security.jwt.util.JwtTokenizer;
import com.spring.project3rd.service.BlockService;
import com.spring.project3rd.service.LikeService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/like")
public class LikeController {
    private final UserLikeRepository likeRepository;
    private final LikeService likeService;
    private final BlockService blockService;
    private final JwtTokenizer jwtTokenizer;

    // 즐겨찾기
    @PostMapping("")
    public Response likeUser(@RequestBody UserLikeId ids){
        String userId = ids.getUserId();
        String likeId = ids.getLikeId();
        // 이미 차단되어 있는 경우 즐겨찾기 불가능
        if(blockService.isUserBlockExists(userId,likeId)){
            return new Response("fail","Blocked user cannot like");
        }else if(likeService.isUserLikeExists(userId,likeId)){
            // 이미 즐겨찾기를 해둔 경우
            return new Response("fail","Already liked");
        }
        UserLike like = new UserLike(userId,likeId);
        try{
            likeRepository.save(like);
            return new Response("success","Like saved successfully.");
        }catch (Exception e){
            return new Response("fail","Error save like");
        }
    }

    // 즐겨찾기 취소
    @DeleteMapping("/cancel")
    public Response likeCancelUser(@RequestBody UserLikeId ids){
        String userID = ids.getUserId();
        String likeId = ids.getLikeId();
        UserLike like = new UserLike(userID,likeId);
        try{
            likeService.likeCancel(like);
            return new Response("success","Like canceled successfully.");
        }catch (Exception e){
            return new Response("fail","Error cancel like");
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
