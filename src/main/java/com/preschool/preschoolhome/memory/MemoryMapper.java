package com.preschool.preschoolhome.memory;


import com.preschool.preschoolhome.memory.model.AllSelMemoryDto;
import com.preschool.preschoolhome.memory.model.AllSelMemoryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemoryMapper {
    AllSelMemoryVo AllMemory(AllSelMemoryDto dto);

}
