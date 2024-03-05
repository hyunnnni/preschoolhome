package com.preschool.preschoolhome.memory;

import com.preschool.preschoolhome.entity.KidEntity;
import com.preschool.preschoolhome.entity.ParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KidRepository extends JpaRepository<KidEntity,Integer>,KidQdslRepository {
    List<KidEntity> findAll();


}
