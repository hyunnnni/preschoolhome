package com.preschool.preschoolhome.memory;


import com.preschool.preschoolhome.memory.model.AllSelMemoryDto;
import com.preschool.preschoolhome.memory.model.AllSelMemoryVo;
import com.preschool.preschoolhome.memory.model.SelMemoryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemoryMapper {
    List<AllSelMemoryVo> allMemoryTea(AllSelMemoryDto dto);
    int allMemoryTeaCnt(AllSelMemoryDto dto);
    List<AllSelMemoryVo> allMemoryPar(AllSelMemoryDto dto);
    int allMemoryParCnt(AllSelMemoryDto dto);
    int iMemoryComment(int imemory);
    List<Integer> iMemoryIkid(int imemory);
    AllSelMemoryVo memory(int imemory);
    // ---------------------- 수정전 정보 불러오기
    SelMemoryVo selMemory(int imemory);
    List<String> selMemoryPic(int imemory);
    String selImemory(int imemory);

}
