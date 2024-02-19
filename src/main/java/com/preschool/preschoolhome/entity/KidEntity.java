package com.preschool.preschoolhome.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "t_kid")
public class KidEntity extends CreatedAtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "INT UNSIGNED")
    private Integer ikid;

    @ManyToOne
    @JoinColumn(name = "iclass", nullable = false)
    private ClassEntity classEntity;

    @Column(length = 10, nullable = false)
    private String kidNm;

    @Column(length = 3000)
    private String profile;

    @Temporal(TemporalType.DATE)
    @Column(length = 10, nullable = false)
    private Date birth;

    @Column(nullable = false)
    private Integer gender;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer state;

    @Column(unique = true, length = 15, nullable = false)
    private String code;

    @Column(length = 3000)
    private String memo;

    @Column(name = "emer_nm", length = 10)
    private String emerNm;

    @Column(name = "emer_nb", length = 20)
    private String emerNb;
}
