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
    @JoinColumn(name = "ikid",columnDefinition = "BIGINT UNSIGNED")
    private KidEntity kidEntity;

    @ManyToOne
    @MapsId("imemory")
    @JoinColumn(name = "imemory", columnDefinition = "BIGINT UNSIGNED")
    private MemoryEntity memoryEntity;




}
