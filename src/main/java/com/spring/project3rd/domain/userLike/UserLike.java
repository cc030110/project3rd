package com.spring.project3rd.domain.userLike;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_like")
public class UserLike {
    @EmbeddedId
    private UserLikeId likeIds;

    public UserLike(String userId, String likeId){
        this.likeIds = new UserLikeId(userId,likeId);
    }
}