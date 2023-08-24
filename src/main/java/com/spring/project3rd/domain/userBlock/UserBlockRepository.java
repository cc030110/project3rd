package com.spring.project3rd.domain.userBlock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserBlockRepository extends JpaRepository<UserBlock, UserBlockId> {
    // userId를 이용하여 blockId만 조회
    @Query(value = "SELECT block_id FROM user_block WHERE user_id = ?1", nativeQuery = true)
    List<String> findBlockIdsByUserId(String userId);
}
