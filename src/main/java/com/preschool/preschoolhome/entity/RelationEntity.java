package com.preschool.preschoolhome.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_family_relation")
public class RelationEntity extends CreatedAtEntity{
    @Id
    @Column(columnDefinition = "INT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer irelation;

    @Column(length = 10, name = "relation", nullable = false)
    private String relation;
    
}
