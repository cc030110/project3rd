package com.spring.project3rd.controller;

import com.spring.project3rd.domain.participant.Participant;
import com.spring.project3rd.domain.participant.ParticipantRepository;
import com.spring.project3rd.payload.Response;
import com.spring.project3rd.security.jwt.util.JwtTokenizer;
import com.spring.project3rd.service.BoardCommunityService;
import com.spring.project3rd.service.ParticipantService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/community/participant")
public class ParticipantController {
    private final ParticipantRepository participantRepository;
    private final ParticipantService participantService;
    private final JwtTokenizer jwtTokenizer;

    private final BoardCommunityService boardCommunityService;

    @PostMapping("/{boardNo}")
    public Response participate(@CookieValue(value = "accessToken", required = false) String accessToken,
                                @PathVariable int boardNo){
        if(accessToken==null)
            return new Response("fail","로그인 후 이용 가능한 서비스입니다.");

        Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
        String id = claims.get("id", String.class);

        Participant participant = participantRepository.findByBoardNoAndParticipantId(boardNo,id);
        if(participant!=null)
            return new Response("fail","이미 신청하였습니다.");

        return new Response("success","완료");

    }

}
