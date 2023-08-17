package com.spring.project3rd.service;

import com.spring.project3rd.domain.block.Block;
import com.spring.project3rd.domain.block.BlockId;
import com.spring.project3rd.domain.block.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlockService {

    private final BlockRepository blockRepository;

    @Transactional
    public void unblock(Block block){
        blockRepository.delete(block);
    }

    // 차단한 유저 리스트
    public List<String> blockList(String id){
        List<String> blockedIds = blockRepository.findBlockIdsByUserId(id);
        return blockedIds;
    }

}
