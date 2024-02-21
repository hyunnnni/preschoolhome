package com.preschool.preschoolhome.memory;

import com.preschool.preschoolhome.entity.KidEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KidRepository extends JpaRepository<KidEntity,Integer> {
    List<KidEntity> findAll();
}
