package com.spring.project3rd.controller;

import com.spring.project3rd.domain.userBlock.UserBlock;
import com.spring.project3rd.domain.userBlock.UserBlockId;
import com.spring.project3rd.domain.userBlock.UserBlockRepository;
import com.spring.project3rd.payload.Response;
import com.spring.project3rd.security.jwt.util.JwtTokenizer;
import com.spring.project3rd.service.BlockService;
import com.spring.project3rd.service.LikeService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class BlockController {

    private final UserBlockRepository blockRepository;
    private final BlockService blockService;
    private final LikeService likeService;

    private final JwtTokenizer jwtTokenizer;

    // 차단
    @PostMapping("/block")
    public Response blockUser(@RequestBody UserBlockId ids){
        String userId = ids.getUserId();
        String blockId = ids.getBlockId();
        // 이미 즐겨찾기가 되어있는 경우 차단 불가능
        if(likeService.isUserLikeExists(userId,blockId)){
            return new Response("fail","Liked user cannot block");
        }else if(blockService.isUserBlockExists(userId,blockId)){
            // 이미 차단해둔 경우
            return new Response("fail","Already blocked");
        }
        UserBlock block = new UserBlock(userId,blockId);
        try {
            blockRepository.save(block);
            return new Response("success","Block saved successfully.");
        } catch (Exception e) {
            return new Response("fail","Error save block");
        }
    }

    // 차단 해제
    @DeleteMapping("/unblock")
    public Response unblockUser(@RequestBody UserBlockId ids){
        String userId = ids.getUserId();
        String blockId = ids.getBlockId();
        UserBlock block = new UserBlock(userId,blockId);
        try {
            blockService.unblock(block);
            return new Response("success","Unblocked successfully.");
        } catch (Exception e) {
            return new Response("fail","Error unblock");
        }
    }

    // 내가 차단한 유저 목록 조회
    @GetMapping("/block/list")
    public List<String> showBlockList(@CookieValue(value = "accessToken", required = false) String accessToken){
        List<String> blockedIds = new ArrayList<>();
        if(accessToken!=null){
            Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
            String id = claims.get("id", String.class);
            blockedIds = blockService.blockList(id);
        }

        return blockedIds;
    }

}