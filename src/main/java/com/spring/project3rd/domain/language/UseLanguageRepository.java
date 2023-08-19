package com.spring.project3rd.domain.language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UseLanguageRepository extends JpaRepository<UseLanguage,Integer> {

    @Query(value = "SELECT * FROM use_language WHERE id=?1",nativeQuery = true)
    List<UseLanguage> findById(String id);
}
