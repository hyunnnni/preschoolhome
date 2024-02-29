package com.preschool.preschoolhome.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "t_memory_comment")
public class MemoryCommentEntity extends CreatedAtEntity {
    @Id
    @Column(name = "imemory_comment", columnDefinition = "INT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imemoryComment;

    @ManyToOne
    @JoinColumn(name = "imemory", columnDefinition = "INT UNSIGNED")
    private MemoryEntity memoryEntity;

    @Column(name = "memory_comment", length = 300)
    private String memoryComment;

    @ColumnDefault("0")
    @ManyToOne
    @Column(nullable = false)
    private TeacherEntity teacher;

    @ColumnDefault("0")
    @ManyToOne
    @Column(nullable = false)
    private ParentEntity parent;
}
