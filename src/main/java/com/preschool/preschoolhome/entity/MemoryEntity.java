package com.preschool.preschoolhome.entity;

import jakarta.persistence.*;
import lombok.Data;

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

    @Column(length = 1000)
    private String contents;




}