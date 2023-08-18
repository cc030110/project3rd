package com.spring.project3rd.domain.platform;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatformRepository extends JpaRepository<Platform,String> {

    public Platform findByPlatformName(String platformName);
}
