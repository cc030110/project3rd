package com.spring.project3rd.controller;

import com.spring.project3rd.domain.boardCommunity.BoardCommunity;
import com.spring.project3rd.domain.participant.Participant;
import com.spring.project3rd.domain.participant.ParticipantRepository;
import com.spring.project3rd.domain.participant.ParticipantRequestDto;
import com.spring.project3rd.payload.Response;
import com.spring.project3rd.security.jwt.util.JwtTokenizer;
import com.spring.project3rd.service.BoardCommunityService;
import com.spring.project3rd.service.ParticipantService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/community/participant")
public class ParticipantController {
    private final ParticipantRepository participantRepository;
    private final ParticipantService participantService;
    private final JwtTokenizer jwtTokenizer;

    private final BoardCommunityService boardCommunityService;


    // 참가 신청
    @PostMapping("/{boardNo}")
    public Response participate(@CookieValue(value = "accessToken", required = false) String accessToken,
                                @PathVariable int boardNo){
        if(accessToken==null)
            return new Response("fail","로그인 후 이용 가능한 서비스입니다.");

        Claims claims = jwtTokenizer.parseToken(accessToken, jwtTokenizer.accessSecret);
        String id = claims.get("id", String.class);

        Participant participant = participantRepository.findByBoardNoAndParticipantId(boardNo,id);
        if(participant!=null&&participant.getIsAccept()==0){
            return new Response("fail","이미 신청하였습니다.");
        }else if(participant!=null&&participant.getIsAccept()==1){
            return new Response("fail","이미 수락된 신청입니다.");
        }else if(participant!=null&&participant.getIsAccept()==2){
            return new Response("fail","이미 거절된 신청입니다.");
        }

        BoardCommunity board = boardCommunityService.getBoard(boardNo);
        if(board==null){
            return new Response("fail","존재하지 않는 게시글입니다.");
        }
        // 수락한 신청 리스트
        List<Participant> list = participantService.getListByBoardNoAndAccept(boardNo,1);
        if(list.size()==board.getParticipantsNum()){
            return new Response("fail","이미 신청자가 가득 찼습니다.");
        }
        // 나중에 현재(로컬)시간과 마감 시간 비교하여 마감 여부도 추가

        Participant newParticipant = new Participant(boardNo,id);
        participantRepository.save(newParticipant);

        return new Response("success","신청되었습니다.");
    }

    // 참가 수락 혹은 거절
    @PutMapping("/accept")
    public Response acceptUser(@RequestBody ParticipantRequestDto requestDto){
        participantService.acceptParticipant(requestDto);
        String result = "";
        if(requestDto.getIsAccept()==1){
            result="수락";
        }else{
            result="거절";
        }
        return new Response("accept",result);
    }

}
