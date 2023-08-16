package com.spring.project3rd.domain.block;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class BlockId implements Serializable {
    private String userId;
    private String blockId;
}
