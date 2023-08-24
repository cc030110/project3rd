package com.spring.project3rd.domain.userBlock;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_block")
public class UserBlock {
    @EmbeddedId
    private UserBlockId id;

    public UserBlock(String userId, String blockId) {
        this.id = new UserBlockId(userId, blockId);
    }

}
