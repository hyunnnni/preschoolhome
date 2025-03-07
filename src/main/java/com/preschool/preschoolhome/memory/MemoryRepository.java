package com.preschool.preschoolhome.memory;

import com.preschool.preschoolhome.entity.MemoryCommentEntity;
import com.preschool.preschoolhome.entity.MemoryEntity;
import com.preschool.preschoolhome.entity.MemoryRoomEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoryRepository extends JpaRepository<MemoryEntity, Integer>,MemoryQdslRepository {
    @EntityGraph(attributePaths = {"teacherEntity"})
    MemoryEntity findAllByImemory(int imemory);


}
