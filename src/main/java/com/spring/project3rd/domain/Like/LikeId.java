package com.spring.project3rd.domain.Like;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

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
}
