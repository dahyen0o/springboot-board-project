package com.fastcamp.projectboard.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditingFields {
    // 공통 column 따로 추출
    @CreatedDate @DateTimeFormat(iso = ISO.DATE_TIME)
    @Column(nullable = false, updatable = false) private LocalDateTime createdAt;
    @CreatedBy
    @Column(nullable = false, updatable = false, length = 100) private String createdBy;
    @LastModifiedDate @DateTimeFormat(iso = ISO.DATE_TIME)
    @Column(nullable = false) private LocalDateTime modifiedAt;
    @LastModifiedBy
    @Column(nullable = false, length = 100) private String modifiedBy;
}
