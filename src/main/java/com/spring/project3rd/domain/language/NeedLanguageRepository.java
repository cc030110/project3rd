package com.spring.project3rd.domain.language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NeedLanguageRepository extends JpaRepository<NeedLanguage,Integer> {

    @Query(value = "SELECT * FROM need_language WHERE id=?1",nativeQuery = true)
    List<NeedLanguage> findById(String id);
}
