package com.spring.project3rd.controller;

import com.spring.project3rd.domain.participant.Participant;
import com.spring.project3rd.domain.participant.ParticipantRepository;
import com.spring.project3rd.security.jwt.util.JwtTokenizer;
import com.spring.project3rd.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/community/participant")
public class ParticipantController {
    private final ParticipantRepository participantRepository;
    private final ParticipantService participantService;
    private final JwtTokenizer jwtTokenizer;
}
