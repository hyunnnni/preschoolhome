package com.preschool.preschoolhome.memory;

import com.preschool.preschoolhome.entity.MemoryCommentEntity;
import com.preschool.preschoolhome.entity.MemoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoryCommentRepository extends JpaRepository<MemoryCommentEntity,Integer>,MemoryQdslRepository {
    List<MemoryCommentEntity> findAllByMemoryEntity(MemoryEntity memoryEntity);


}

