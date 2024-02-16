package com.preschool.preschoolhome.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_memory")
public class MemoryEntity extends BaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //오토인클리먼트
    private Long imemory;

    @ManyToOne
    @JoinColumn(name = "iparent",nullable = false)
    private ParEntity parEntity;

    @Column(length = 1000)
    private String contents;




}
