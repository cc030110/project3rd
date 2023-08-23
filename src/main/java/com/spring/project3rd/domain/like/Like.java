package com.spring.project3rd.domain.like;

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
@Table(name = "like")
public class Like {
    @EmbeddedId
    private LikeId likeId;

    public Like(String userId, String likeId){
        this.likeId = new LikeId(userId,likeId);
    }

}
