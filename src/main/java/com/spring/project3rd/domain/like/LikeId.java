package com.spring.project3rd.domain.like;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class LikeId implements Serializable {
    @Column(length = 20)
    private String userId;
    @Column(length = 20)
    private String likeId;

    // 복합키의 경우 equals와 hashCode를 override 필요
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LikeId likeId1 = (LikeId) obj;
        return Objects.equals(userId, likeId1.userId) && Objects.equals(likeId, likeId1.likeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, likeId);
    }

}
