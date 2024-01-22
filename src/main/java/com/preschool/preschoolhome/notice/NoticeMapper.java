package com.preschool.preschoolhome.notice;

import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.notice.model.*;
import com.preschool.preschoolhome.notice.model.sel.NoticeUpdSelVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface NoticeMapper {
    //알림장 등록
    int insNotice(NoticeInsDto dto);
    int insNoticePics(NoticePicsInsDto dto);
    //알림장 수정 시 내용 조회
    NoticeUpdSelVo noticeEdit(int inotice, int ikid);
    List<String> noticeEditPics(int inotice);
    //알림장 수정
    int updNotice(NoticeUpdDto dto);
    int delNoticePics(int inotice);

    //알림장 삭제
    int delNotice(int iteacher, int inotice);
    int delAllNotice(int iteacher, int inotice);

    //------------------------ 학부모 유저 시점 원아 알림장 전체 조회 ------------------------
    List<SelAllNoticeVo> selAllNoticeBoardPar(SelAllNoticeDto dto);
    //------------------------ 선생님 유저 시점 원아 알림장 전체 조회 ------------------------
    List<SelAllNoticeVo> selAllNoticeBoardTea(SelAllNoticeDto dto);
    //------------------------ 알림장 전체 조회 시 해당 알림장의 사진 유/무 체크 ------------------------
    Integer selNoticeBoardPicCheck(int inotice);



}
