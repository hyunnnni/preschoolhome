package com.preschool.preschoolhome.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "t_parent")
public class ParentEntity extends CreatedAtEntity{
    @Id
    @Column(columnDefinition = "INT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iparent;

    @ManyToOne
    @JoinColumn(name = "irelation", nullable = false)
    private RelationEntity relationEntity;

    @Column(length = 10, name = "parent_nm", nullable = false)
    private String parentNm;

    @Column(length = 20, unique = true, name = "uid", nullable = false)
    private String uid;

    @Column(length = 400, name = "upw", nullable = false)
    private String upw;

    @Column(length = 15, name = "phone_nb")
    private String phoneNb;

    @Column(length = 4, name = "duplication_check", nullable = false)
    private Integer duplicationCheck;

    @Column(length = 4, name = "pr_is_del", nullable = false)
    @ColumnDefault("0")
    private Integer prIsDel;

    @Column(length = 30, name = "pr_email", nullable = false)
    private String prEmail;

    @Column(length = 2100, name = "pr_firebase_token")
    private String prFirebaseToken;

    @Column(length = 50, name = "pr_role", nullable = false)
    @ColumnDefault("'PARENT'")
    private String prRole;

}
