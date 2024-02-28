package com.preschool.preschoolhome.memory;


import com.preschool.preschoolhome.memory.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemoryMapper {
    //------------------------------------- 추억앨범 전체 조회 선생님 기준 -------------------------------------
    List<AllSelMemoryVo> allMemoryTea(AllSelMemoryDto dto);
    //------------------------------------- 추억앨범 전체 조회 선생님 기준(글 개수) -------------------------------------
    int allMemoryTeaCnt(AllSelMemoryDto dto);
    //------------------------------------- 추억앨범 전체 조회 학부모 기준 -------------------------------------
    List<AllSelMemoryVo> allMemoryPar(AllSelMemoryDto dto);
    //------------------------------------- 추억앨범 전체 조회 선생님 기준(글 개수) -------------------------------------
    int allMemoryParCnt(AllSelMemoryDto dto);
    //------------------------------------- 추억앨범에 걸린 댓글 불러오기  -------------------------------------
    List<MemoryCommentVo> memoryComment(int imemory);
    //------------------------------------- 추억앨범에 걸린 원아 PK 불러오기 -------------------------------------
    List<Integer> iMemoryIkid(int imemory);
    //------------------------------------- 상세 내용 불러오기 -------------------------------------
    AllSelMemoryVo memory(int imemory);
    //------------------------------------- 수정 전 원래 있던 내용 불러오기 -------------------------------------
    SelMemoryVo selMemory(int imemory);
    //------------------------------------- 추억앨범 사진 조회 -------------------------------------
    List<String> selMemoryPic(int imemory);
    //------------------------------------- 추억앨범 pk 조회  -------------------------------------
    Integer selImemory(int imemory);
    //------------------------------------- 추억 앨범 기존에 태그된 원아 -------------------------------------
    List<Integer> selMemoryKid(int imemory);

    //------------------------------------- 추억 앨범 글 작성 시 원아 전체 조회 -------------------------------------
    List<MemorySelVo> getFromKids();
    //------------------------------------- 추억 앨범 글 삭제 전 댓글, 사진, 방 삭제 -------------------------------------
    int delMemoryAll(int imemory);
    //------------------------------------- 추억 앨범 글 삭제 -------------------------------------
    int delMemory(int imemory);
    //-------------------------------- 추억 앨범 글 등록 --------------------------------
    int insMemory(InsMemoryDto dto);
    //-------------------------------- 원아와 연결된 부모의 토큰값 구하기  --------------------------------
    List<SelMemoryOtherTokens> selParFirebaseByLoginUser(List<Integer> ikids);
    //-------------------------------- 태그된 부모들 추억 앨범 글에 초대  --------------------------------
    int insMemoryRoomInvite(InsRoomInviteProcDto dto);
    //-------------------------------- 추억 앨범 사진 등록  --------------------------------
    int insMemoryPic(InsMemoryPicsDto picsDto);
    //-------------------------------- 추억 앨범 댓글 등록 ----------------------------------
    int insComment(InsCommentDto dto);
    //-------------------------------- 추억 앨범 댓글 삭제  --------------------------------
    int delMemoryComment(DelMemoryCommentDto dto);
    //------------------ 추억앨범 댓글 작성 시 푸시를 받을 학부모의 토큰값과 pk값 조회 ----------------------
    String selTeaFirebaseByLoginUserComment(int imemory);
    //-------------------추억앨범 댓글 작성 시 푸시를 받을 선생님의 토큰값과 pk값 조회  ----------------------
    String selParFirebaseByLoginUserComment(int imemory);
    //------------------------------------- 추억 앨범 수정 시 푸시를 받을 추가된 아이들의 학부모 토큰값과 pk값 조회 -------------------------------------
    List<SelMemoryOtherTokens> selTeaFirebaseParents(MemoryUpdDto dto);
    //------------------------------------- 추억 앨범 글 수정 -------------------------------------
    int updMemory(MemoryUpdDto dto);
    //------------------------------------- 추억 앨범 수정 시 사진 삭제 -------------------------------------
    int delMemoryPic(MemoryUpdDto dto);
    //------------------------------------- 추억 앨범 사진 등록 -------------------------------------
    int insPicsMemory(MemoryPicsInsDto dto);
    //------------------------------------- 추억 앨범 삭제 전 글 확인 -------------------------------------
    int selDelAlbum (int imemory);
    //------------------------------------- 추억 앨범 수정 시 선택 해제 원아 room에서 지우기 -------------------------------------
    int delKidRoom (int imemory);

}
