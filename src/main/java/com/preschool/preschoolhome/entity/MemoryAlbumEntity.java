package com.preschool.preschoolhome.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "t_memory_album")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemoryAlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imemory_pic", nullable = false)
    private Integer imemoryPic;

    @ManyToOne
    @JoinColumn(name = "imemory", nullable = false)
    private MemoryEntity memoryEntity;

    @Column(name = "memory_pic", length = 2100, nullable = false)
    private String memoryPic;
}
