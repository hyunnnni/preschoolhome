package com.preschool.preschoolhome.memory;


import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.entity.MemoryAlbumEntity;
import com.preschool.preschoolhome.entity.MemoryCommentEntity;
import com.preschool.preschoolhome.entity.MemoryEntity;
import com.preschool.preschoolhome.memory.model.AllSelMemoryDto;
import com.preschool.preschoolhome.memory.model.DelMemoryCommentDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemoryQdslRepository {
    List<MemoryEntity> selMemoryAll(AllSelMemoryDto dto);

    MemoryEntity findAllByKidPksAndImemory(List<Integer> kidPks, int imemory);
    List<MemoryAlbumEntity> selMemoryPicsAll(List<MemoryEntity> memoryEntityList);

    List<MemoryCommentEntity> selMemoryCommentAll(List<MemoryEntity> memoryEntityList);

    ResVo delMemoryComment(DelMemoryCommentDto dto);

    List<MemoryCommentEntity> findAllByMemoryEntity(MemoryEntity memoryEntity);

}
