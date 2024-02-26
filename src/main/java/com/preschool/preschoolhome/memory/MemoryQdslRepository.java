package com.preschool.preschoolhome.memory;


import com.preschool.preschoolhome.entity.MemoryAlbumEntity;
import com.preschool.preschoolhome.entity.MemoryEntity;
import com.preschool.preschoolhome.memory.model.AllSelMemoryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemoryQdslRepository {
    List<MemoryEntity> selMemoryAll(AllSelMemoryDto dto, Pageable pageable);

    List<MemoryAlbumEntity> selFeedPicsAll(List<MemoryEntity> feedEntityList);

}
