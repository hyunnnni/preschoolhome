package com.preschool.preschoolhome.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@EntityListeners(AuditingEntityListener.class)//엔터티 적용 전에 콜백으로AuditingEntityListener를 호출해 공통적으로 처리
//@EntityListeners(AuditingEntityListener.class) 날짜 시간 관리
@MappedSuperclass
public class BaseEntity extends CreatedAtEntity {
    @LastModifiedDate
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private LocalDateTime updatedAt;
}
