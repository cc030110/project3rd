package com.spring.project3rd.domain.block;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BlockId implements Serializable{
    @Column(length = 20)
    private String userId;

    @Column(length = 20)
    private String blockId;

    // 복합키의 경우 equals와 hashCode를 override 필요
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BlockId blockId1 = (BlockId) obj;
        return Objects.equals(userId, blockId1.userId) && Objects.equals(blockId, blockId1.blockId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, blockId);
    }
}
