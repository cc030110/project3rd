package com.spring.project3rd.service;

import com.spring.project3rd.domain.boardCommunity.BoardCommunityRepository;
import com.spring.project3rd.domain.participant.ParticipantRepository;
import com.spring.project3rd.domain.participant.ParticipantRequestDto;
import com.spring.project3rd.domain.user.User;
import com.spring.project3rd.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;

}
