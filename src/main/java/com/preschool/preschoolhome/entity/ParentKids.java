package com.preschool.preschoolhome.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode
public class ParentKids implements Serializable  {
    private Integer iparent;
    private Integer ikid;
}
