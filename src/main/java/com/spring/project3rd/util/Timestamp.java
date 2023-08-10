package com.spring.project3rd.util;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Timestamp {
    // 등록일
    @Column(name="created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    // 수정일
    @Column(name="modified_at")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

}
