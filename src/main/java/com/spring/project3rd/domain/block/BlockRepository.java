package com.spring.project3rd.domain.block;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block,BlockId> {

    // userId를 이용하여 조회
    public List<Block> findByIdUserId(String userId);


    // userId를 이용하여 blockId만 조회
    @Query(value = "SELECT block_id FROM block WHERE user_id = ?1", nativeQuery = true)
    public List<String> findBlockIdsByUserId(String userId);

}
