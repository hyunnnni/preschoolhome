package com.preschool.preschoolhome.memory;

import com.preschool.preschoolhome.entity.MemoryCommentEntity;
import com.preschool.preschoolhome.entity.MemoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemoryCommentRepository extends JpaRepository<MemoryCommentEntity, Integer>, MemoryQdslRepository {

    @Modifying
    @Query("delete from MemoryCommentEntity a where a.memoryEntity.imemory = :imemory")
    void deleteByMemoryEntity(int imemory);


}

