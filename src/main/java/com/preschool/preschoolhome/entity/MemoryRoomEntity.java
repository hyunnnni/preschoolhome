package com.preschool.preschoolhome.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_memory_room")
public class MemoryRoomEntity {
    @EmbeddedId
    private MemoryRooms memoryRooms;

    @ManyToOne
    @MapsId("ikid")
    @JoinColumn(name = "ikid",columnDefinition = "INT UNSIGNED")
    private KidEntity kidEntity;

    @ManyToOne
    @MapsId("imemory")
    @JoinColumn(name = "imemory", columnDefinition = "INT UNSIGNED")
    private MemoryEntity memoryEntity;




}
