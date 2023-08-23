package com.spring.project3rd.domain.participant;

import com.spring.project3rd.domain.platform.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ParticipantRepository extends JpaRepository<Platform,String> {
    // user id를 이용하여 participant id 조회
    public List<String> findParticipantIdByUserId(String userId);



}
