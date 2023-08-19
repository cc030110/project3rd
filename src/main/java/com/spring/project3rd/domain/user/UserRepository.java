package com.spring.project3rd.domain.user;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
//    public User findById(String id);

    User findByEmail(String email);

    User findByName(String name);

    /** 프로필 게시판 페이징**/
    List<User> findAllByIdLike(String pattern, Pageable pageable);

    @EntityGraph(attributePaths ="authorities")
    Optional<User> findOneWithAuthoritiesById(String id);

    // is_active=0 인 유저 id 리스트
    @Query(value = "SELECT `id` FROM `user` WHERE `is_active`=0",nativeQuery = true)
    List<String> findInactiveUserIds();

}

