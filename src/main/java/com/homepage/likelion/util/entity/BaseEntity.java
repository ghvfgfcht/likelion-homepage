package com.homepage.likelion.util.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter

// 부모 클래스에 이 어노테이션을 붙이면 해당 클래스의 필드와 매핑 정보를 자식 엔티티에 상속 가능하다.
@MappedSuperclass

// jpa 에서 엔티티 생명주기 이벤트를 처리하기 위한 어노테이션이다.
// 이 클래스를 상속받은 클래스에 대해 AuditingEntityListener 클래스가 엔티티의 생성 및 변경을 감지하고 관리한다.
// 이 기능을 사용하기 위해서는 Main Application 파일에서 JpaAuditing 을 활성화해주어야 한다.
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}