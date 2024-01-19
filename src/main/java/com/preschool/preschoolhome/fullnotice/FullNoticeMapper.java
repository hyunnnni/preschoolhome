package com.preschool.preschoolhome.fullnotice;

import com.preschool.preschoolhome.fullnotice.model.SelFullNoticeDto;
import com.preschool.preschoolhome.fullnotice.model.SelFullNoticeVo;
import com.preschool.preschoolhome.fullnotice.model.SelNoticeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FullNoticeMapper {

    //-----------게시판 전체 조회 및 상세조회
    List<SelFullNoticeVo> getFullNoticeFix();
    List<SelFullNoticeVo> getFullNoticelist(SelFullNoticeDto dto);

    List<String> selNoticePic(int iFullNotice); //notice안 사진 불러오기
    SelNoticeVo getNotice(int iFullNotice); //notice불러오기

}
