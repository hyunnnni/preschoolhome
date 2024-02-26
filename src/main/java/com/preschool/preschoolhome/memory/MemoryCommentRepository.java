package com.preschool.preschoolhome.memory;

import com.preschool.preschoolhome.entity.MemoryCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryCommentRepository extends JpaRepository<MemoryCommentEntity,Integer>,MemoryQdslRepository {
}
