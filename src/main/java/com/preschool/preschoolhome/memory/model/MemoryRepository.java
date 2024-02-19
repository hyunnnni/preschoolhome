package com.preschool.preschoolhome.memory.model;

import com.preschool.preschoolhome.entity.MemoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryRepository extends JpaRepository<MemoryEntity, Integer> {
}
