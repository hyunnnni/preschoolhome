package com.preschool.preschoolhome.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_parent_kid")
public class ParentKidEntity {
    @EmbeddedId
    private ParentKids parentKids;

    @ManyToOne
    @MapsId("ikid")
    @JoinColumn(name = "ikid",columnDefinition = "INT UNSIGNED")
    private KidEntity kidEntity;

    @ManyToOne
    @MapsId("iparent")
    @JoinColumn(name = "iparent", columnDefinition = "INT UNSIGNED")
    private ParentEntity parentEntity;




}
