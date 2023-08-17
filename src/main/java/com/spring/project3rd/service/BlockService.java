package com.spring.project3rd.service;

import com.spring.project3rd.domain.block.Block;
import com.spring.project3rd.domain.block.BlockId;
import com.spring.project3rd.domain.block.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlockService {

    private final BlockRepository blockRepository;

    // 블록 추가
    public Block addBlock(String userId, String blockId) {
        BlockId blockIdObject = new BlockId();
        blockIdObject.setUserId(userId);
        blockIdObject.setBlockId(blockId);
        Block block = new Block(blockIdObject);

        return blockRepository.save(block);
    }

}
