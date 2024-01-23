package com.preschool.preschoolhome.fullnotice;

import com.preschool.preschoolhome.fullnotice.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FullNoticeMapper {

    //-----------게시판 전체 조회 및 상세조회
    List<SelFullNoticeVo> getFullNoticeFix();
    List<SelFullNoticeVo> getFullNoticelist(SelFullNoticeDto dto);

    List<String> selNoticePic(int iFullNotice); //notice안 사진 불러오기
    SelNoticeVo getNotice(int iFullNotice); //notice불러오기

    //-------------------------------- 유치원 소식 작성 --------------------------------
    int postFullNotice(InsFullNoticeDto dto);
    //-------------------------------- 유치원 소식 사진 작성 --------------------------------
    int postFullNoticePics(InsFullPicsDto dto);
    //-------------------------------- 유치원 소식 작성자 pk 조회 --------------------------------
    Integer selFullNoticeWriter(int ifullNotice);
    //-------------------------------- 유치원 소식 삭제 --------------------------------
    int delFullNotice(DelFullNoticeDto dto);
    //-------------------------------- 유치원 소식 사진 삭제 --------------------------------
    int delFullNoticePics(DelFullNoticeDto dto);
    //-------------------------------- 유치원 소식 수정 --------------------------------
    int putFullNotice(UpdFullNoticeDto dto);
    //-------------------------------- 유치원 소식 업데이트 시 사진 삭제 --------------------------------
    int delUpdFullNoticePics(int ifullNotice);
    //-------------------------------- 유치원 소식 사진 개수 조회 --------------------------------
    Integer selFullNoticePics(int ifullNotice);
    //-------------------------------- 유치원 소식 상단 고정 기능 조회 --------------------------------
    Integer selNoticeFix(int ifullNotice);
    //-------------------------------- 유치원 소식 수정 시 불러오기 --------------------------------
    SelFullNoticeUpdVo selFullNoticeUpd(int ifullNotice);
    //-------------------------------- 유치원 소식 수정 시 사진 불러오기  --------------------------------
    List<String> selFullNoticeUpdPics(int ifullNotice);
}
