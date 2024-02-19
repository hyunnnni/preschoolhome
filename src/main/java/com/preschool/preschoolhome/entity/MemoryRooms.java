package com.preschool.preschoolhome.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode
public class MemoryRooms implements Serializable  {
    private Long imemory;
    private Long ikid;
}
