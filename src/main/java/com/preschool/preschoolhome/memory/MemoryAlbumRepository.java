package com.preschool.preschoolhome.memory;

import com.preschool.preschoolhome.entity.MemoryAlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MemoryAlbumRepository extends JpaRepository<MemoryAlbumEntity, Long> {
    @Modifying
    @Query("delete from MemoryAlbumEntity a where a.memoryEntity.imemory = :imemory")
    void deleteByMemoryEntity(int imemory);
}
