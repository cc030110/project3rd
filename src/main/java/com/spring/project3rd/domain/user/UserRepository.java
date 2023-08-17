package com.spring.project3rd.domain.user;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
//    public User findById(String id);

    public User findByEmail(String email);

    /** 프로필 게시판 페이징**/
    public List<User> findAllByIdLike(String pattern, Pageable pageable);

    @EntityGraph(attributePaths ="authorities")
    Optional<User> findOneWithAuthoritiesById(String id);

    // is_active=0 인 유저 id 리스트
    @Query(value = "SELECT `id` FROM `user` WHERE `is_active`=0",nativeQuery = true)
    public List<String> findInactiveUserIds();

}

