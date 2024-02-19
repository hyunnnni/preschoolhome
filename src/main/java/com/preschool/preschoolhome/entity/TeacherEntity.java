package com.preschool.preschoolhome.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "t_teacher")
public class TeacherEntity extends CreatedAtEntity{
    @Id
    @Column(columnDefinition = "INT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iteacher;

    @ManyToOne
    @JoinColumn(name = "iclass", nullable = false)
    private ClassEntity classEntity;

    @Column(length = 10, name = "teacher_nm", nullable = false)
    private String teacherNm;

    @Column(length = 20, unique = true, name = "teacher_uid", nullable = false)
    private String teacherUid;

    @Column(length = 400, name = "teacher_upw", nullable = false)
    private String teacherUpw;

    @Column(length = 2000, name = "teacher_introduce")
    private String teacherIntroduce;

    @Column(length = 3000, name = "teacher_profile", nullable = false)
    private String teacherProfile;

    @Column(length = 4, name = "tc_is_del", nullable = false)
    @ColumnDefault("0")
    private Integer tcIsDel;

    @Column(length = 30, name = "tc_email", nullable = false)
    private String tcEmail;

    @Column(length = 2100, name = "tc_firebase_token")
    private String tcFirebaseToken;

    @Column(length = 50, name = "tc_role", nullable = false)
    @ColumnDefault("'TEACHER'")
    private String tcRole;

}
