package com.preschool.preschoolhome.notice;

import com.preschool.preschoolhome.notice.model.NoticeInsDto;
import com.preschool.preschoolhome.notice.model.NoticePicsInsDto;
import com.preschool.preschoolhome.notice.model.sel.NoticeUpdSelVo;
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
}
