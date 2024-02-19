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
    @MapsId("imemory")
    @JoinColumn(name = "imemory", columnDefinition = "BIGINT UNSIGNED", nullable = false)
    private MemoryEntity memoryPk;

    @ManyToOne
    @MapsId("ikid")
    @JoinColumn(name = "ikid",columnDefinition = "BIGINT UNSIGNED", nullable = false)
    private KidEntity kidPk;


}
