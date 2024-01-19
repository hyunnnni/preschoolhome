package com.preschool.preschoolhome.notice;

import com.preschool.preschoolhome.notice.model.NoticeInsDto;
import com.preschool.preschoolhome.notice.model.NoticePicsInsDto;
import com.preschool.preschoolhome.notice.model.SelAllNoticeDto;
import com.preschool.preschoolhome.notice.model.SelAllNoticeVo;
import com.preschool.preschoolhome.notice.model.sel.NoticeUpdSelVo;
import com.preschool.preschoolhome.notice.model.sel.SelFullNoticeDto;
import com.preschool.preschoolhome.notice.model.sel.SelFullNoticeVo;
import com.preschool.preschoolhome.notice.model.sel.SelNoticeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    //알림장 등록
    int insNotice(NoticeInsDto dto);
    int insNiticePics(NoticePicsInsDto dto);
    //알림장 수정 시 내용 조회
    NoticeUpdSelVo noticeEdit(int inotice, int ikid);
    List<String> noticeEditPics(int inotice);
    //알림장 삭제
    int delNotice(int iteacher, int inotice);
    int delAllNotice(int iteacher, int inotice);

    //------------------------ 학부모 유저 시점 원아 알림장 전체 조회 ------------------------
    List<SelAllNoticeVo> selAllNoticeBoardPar(SelAllNoticeDto dto);
    //------------------------ 선생님 유저 시점 원아 알림장 전체 조회 ------------------------
    List<SelAllNoticeVo> selAllNoticeBoardTea(SelAllNoticeDto dto);
    //------------------------ 알림장 전체 조회 시 해당 알림장의 사진 유/무 체크 ------------------------
    Integer selNoticeBoardPicCheck(int inotice);

    //-----------게시판 전체 조회 및 상세조회
    List<SelFullNoticeVo> getFullNoticeFix();
    List<SelFullNoticeVo> getFullNoticelist(SelFullNoticeDto dto);

    List<String> selNoticePic(int iFullNotice); //notice안 사진 불러오기
    SelNoticeVo getNotice(int iFullNotice); //notice불러오기

}
