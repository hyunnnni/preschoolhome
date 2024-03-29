package com.preschool.preschoolhome.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "t_memory")
public class MemoryEntity extends BaseEntity {
    @Id
    @Column(columnDefinition = "INT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //오토인클리먼트
    private Integer imemory;

    @ManyToOne
    @JoinColumn(name = "iteacher",nullable = false)
    private TeacherEntity teacherEntity;

    @Column(length = 100,name = "memoryTitle")
    private String title;

    @Column(length = 1000, name = "memoryContents")
    private String contents;

    @ToString.Exclude
    @OneToMany(mappedBy = "memoryEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MemoryAlbumEntity> memoryAlbumEntityList = new ArrayList();

    @ToString.Exclude
    @OneToMany(mappedBy = "memoryEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MemoryCommentEntity> memoryCommentEntityList = new ArrayList();

    @BatchSize(size = 1000)
    @ToString.Exclude
    @OneToMany(mappedBy = "memoryEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MemoryRoomEntity> memoryRoomEntityList = new ArrayList();

    public LocalDateTime createdAt() {
        return super.getCreatedAt();
    }
}
