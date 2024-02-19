package com.preschool.preschoolhome.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_class")
public class ClassEntity extends CreatedAtEntity{
    @Id
    @Column(columnDefinition = "INT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iclass;

    @Column(length = 10, name = "class_nm", nullable = false)
    private String classNm;
    
}
