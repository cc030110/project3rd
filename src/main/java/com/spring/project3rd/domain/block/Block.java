package com.spring.project3rd.domain.block;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "block")
public class Block {
    @EmbeddedId
    private BlockId id;

    public Block(String userId, String blockId) {
        this.id = new BlockId(userId, blockId);
    }
}
