package com.spring.project3rd.domain.block;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

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
}
