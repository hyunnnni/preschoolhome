package com.preschool.preschoolhome.memory;


import com.preschool.preschoolhome.memory.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemoryMapper {
    List<AllSelMemoryVo> allMemoryTea(AllSelMemoryDto dto);
    int allMemoryTeaCnt(AllSelMemoryDto dto);
    List<AllSelMemoryVo> allMemoryPar(AllSelMemoryDto dto);
    int allMemoryParCnt(AllSelMemoryDto dto);
//    int iMemoryComment(int imemory);
    List<Integer> iMemoryIkid(int imemory);
    AllSelMemoryVo memory(int imemory);
    List<MemoryCommentVo> memoryComment(int imemory);
    // ---------------------- 수정전 정보 불러오기
    SelMemoryVo selMemory(int imemory);
    List<String> selMemoryPic(int imemory);
    String selImemory(int imemory);
    List<Integer> selMemoryKid(int imemory);



    //------------------------------------- 추억 앨범 글 작성 시 전체 원아 조회 -------------------------------------
    List<MemorySelDto> getFromKids();
    //------------------------------------- 추억 앨범 글 삭제 전 댓글, 사진, 방 삭제 -------------------------------------
    int delMemoryAll(int imemory);
    //------------------------------------- 추억 앨범 글 삭제 -------------------------------------
    int delMemory(int imemory);


}
