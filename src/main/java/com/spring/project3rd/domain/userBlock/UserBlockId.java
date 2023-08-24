package com.spring.project3rd.domain.userBlock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserBlockId implements Serializable {
    @Column(length = 20)
    private String userId;

    @Column(length = 20)
    private String blockId;

    // 복합키의 경우 equals와 hashCode를 override 필요
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserBlockId userBlockId = (UserBlockId) obj;
        return Objects.equals(userId, userBlockId.userId) && Objects.equals(blockId, userBlockId.blockId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, blockId);
    }
}
