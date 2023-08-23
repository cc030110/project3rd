package com.spring.project3rd.domain.like;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,LikeId> {

    // userId를 이용하여 likeId만 조회
    @Query(value = "SELECT like_id FROM `like` WHERE user_id = ?1", nativeQuery = true)
    public List<String> findLikeIdsByUserId(String userId);
}
