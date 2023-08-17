package com.spring.project3rd.controller;

import com.spring.project3rd.domain.block.Block;
import com.spring.project3rd.domain.block.BlockId;
import com.spring.project3rd.domain.block.BlockRepository;
import com.spring.project3rd.security.jwt.util.JwtTokenizer;
import com.spring.project3rd.service.BlockService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class BlockController {

    private final BlockRepository blockRepository;
    private final BlockService blockService;

    private final JwtTokenizer jwtTokenizer;

    // 차단
    @PostMapping("/block")
    public ResponseEntity<String> blockUser(@RequestBody BlockId ids){
        String userId = ids.getUserId();
        String blockId = ids.getBlockId();
        Block block = new Block(userId,blockId);
        try {
            blockRepository.save(block);
            return ResponseEntity.status(HttpStatus.OK).body("Block saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error save block.");
        }
    }

    // 차단 해제
    @DeleteMapping("/unblock")
    public ResponseEntity<String> unblockUser(@RequestBody BlockId ids){
        String userId = ids.getUserId();
        String blockId = ids.getBlockId();
        Block block = new Block(userId,blockId);
        try {
            blockService.unblock(block);
            return ResponseEntity.status(HttpStatus.OK).body("Block saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error delete block.");
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