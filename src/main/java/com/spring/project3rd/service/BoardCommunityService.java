package com.spring.project3rd.service;

import com.spring.project3rd.domain.boardCommunity.BoardCommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCommunityService {

    private final BoardCommunityRepository boardCommunityRepository;
    // @ Transactional
}
