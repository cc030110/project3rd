package com.spring.project3rd.domain.block;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Table(name = "block")
public class Block {
    @Id
    private String userId;
    @Id
    private String blockId;
}
