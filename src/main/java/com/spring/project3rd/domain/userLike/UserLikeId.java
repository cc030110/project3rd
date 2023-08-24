package com.spring.project3rd.domain.userLike;

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
public class UserLikeId implements Serializable {
    @Column(length = 20)
    private String userId;
    @Column(length = 20)
    private String likeId;

    // 복합키의 경우 equals와 hashCode를 override 필요
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserLikeId userLikeId = (UserLikeId) obj;
        return Objects.equals(userId, userLikeId.userId) && Objects.equals(likeId, userLikeId.likeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, likeId);
    }
}
