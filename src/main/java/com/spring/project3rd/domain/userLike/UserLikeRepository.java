package com.spring.project3rd.domain.userLike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserLikeRepository extends JpaRepository<UserLike,UserLikeId> {
    // userId를 이용하여 likeId만 조회
    @Query(value = "SELECT like_id FROM `like` WHERE user_id = ?1", nativeQuery = true)
    List<String> findLikeIdsByUserId(String userId);
}