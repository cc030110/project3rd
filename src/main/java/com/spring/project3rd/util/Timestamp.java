package com.spring.project3rd.util;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Timestamp {
    // 등록일
    @CreatedDate
    private LocalDateTime createdAt;

    // 수정일
    @LastModifiedDate
    private LocalDateTime modifiedAt;

}
