package com.preschool.preschoolhome.memory;



import com.preschool.preschoolhome.entity.KidEntity;

import com.preschool.preschoolhome.memory.model.AllSelMemoryDto;
import com.preschool.preschoolhome.memory.model.DelMemoryCommentDto;

import java.util.List;

public interface KidQdslRepository {
    List<KidEntity> selKidByIparent(int iparent);



}
