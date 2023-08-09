package com.spring.project3rd.service;

import com.spring.project3rd.domain.boardFree.BoardFreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardFreeService {

    private final BoardFreeRepository boardFreeRepository;
    // @Transactional

}
