package com.spring.project3rd.service;

import com.spring.project3rd.domain.boardFree.BoardFreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardFreeService {
    private final BoardFreeRepository boardFreeRepository;

    @Transactional
    public void deleteBoard(int no){
        boardFreeRepository.deleteById(no);
    }
}
