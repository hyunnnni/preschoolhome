package com.preschool.preschoolhome.memory;

import com.preschool.preschoolhome.entity.MemoryEntity;
import com.preschool.preschoolhome.entity.MemoryRoomEntity;
import com.preschool.preschoolhome.entity.MemoryRooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoryRoomRepository extends JpaRepository<MemoryRoomEntity, MemoryRooms> {
    List<MemoryRoomEntity> findAllByMemoryEntity(MemoryEntity memoryEntity);

}