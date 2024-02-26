package com.preschool.preschoolhome.memory;

import com.preschool.preschoolhome.entity.KidEntity;
import com.preschool.preschoolhome.entity.MemoryEntity;
import com.preschool.preschoolhome.entity.TeacherEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoryRepository extends JpaRepository<MemoryEntity, Integer>{//,MemoryQdslRepository {
    @EntityGraph(attributePaths = {"teacherEntity"})
    List<MemoryEntity> findAllByTeacherEntityOrderByImemoryDesc(TeacherEntity teacherEntity);




}
