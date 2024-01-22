package com.preschool.preschoolhome.notice;

import com.preschool.preschoolhome.common.exception.AuthErrorCode;
import com.preschool.preschoolhome.common.exception.RestApiException;
import com.preschool.preschoolhome.common.security.AuthenticationFacade;
import com.preschool.preschoolhome.common.utils.Const;
import com.preschool.preschoolhome.common.utils.MyFileUtils;
import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.notice.model.*;
import com.preschool.preschoolhome.notice.model.sel.NoticeUpdSelVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeMapper mapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;
    //알림장 등록
    ResVo insNotice(List<MultipartFile> pics, NoticeInsDto dto){
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);
        if(dto.getIlevel()<2){
            return new ResVo(Const.FAIL);
        }
        mapper.insNotice(dto);
        String target = "/notice/"+dto.getInotice();

        NoticePicsInsDto picsDto = new NoticePicsInsDto();
        picsDto.setInotice(dto.getInotice());
        for (MultipartFile file: pics) {
            String saveFileNm = myFileUtils.transferTo(file, target);
            picsDto.getPics().add(saveFileNm);
        }
        mapper.insNoticePics(picsDto);
        return new ResVo(Const.SUCCESS);
    }

    //알림장 수정 시 정보 출력
    public NoticeUpdSelVo noticeEdit(int inotice, int ikid){
        NoticeUpdSelVo vo = mapper.noticeEdit(inotice,ikid);
        List<String> pics = mapper.noticeEditPics(inotice);
        vo.setNoticePics(pics);
        return vo;

    }

    //알림장 수정
    public ResVo updNotice(List<MultipartFile> pics, NoticeUpdDto dto){
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        String target = "/notice/"+dto.getInotice();

        if(dto.getIlevel() < 2){
            return new ResVo(Const.FAIL);
        }
        int affectedRows = mapper.updNotice(dto);
        if(affectedRows ==0){
            return new ResVo(Const.FAIL);
        }

        int affectedDelRows = mapper.delNoticePics(dto.getInotice());
        if(affectedDelRows == 0){
            return new ResVo(Const.FAIL);
        }
        NoticePicsInsDto picsDto = new NoticePicsInsDto();
        picsDto.setInotice(dto.getInotice());
        for (MultipartFile file: pics) {
            String saveFileNm = myFileUtils.transferTo(file, target);
            picsDto.getPics().add(saveFileNm);
        }
        int affectedPicRows =  mapper.insNoticePics(picsDto);
        if(affectedPicRows == 0){
            return new ResVo(Const.FAIL);
        }
        return new ResVo(Const.SUCCESS);
    }

    //알림장 삭제
    ResVo delNotice(int iteacher, int inotice){
        int level = authenticationFacade.getLevelPk();
        if(level != 2){
            return new ResVo(Const.FAIL);
        }
        mapper.delAllNotice(iteacher, inotice);
        mapper.delNotice(iteacher, inotice);
        return new ResVo(Const.SUCCESS);
    }

    //-------------------------------- 알림장 접근 유저에 따라 다르게 전체 조회 --------------------------------
    public List<SelAllNoticeVo> getKidManagement(SelAllNoticeDto dto){

        List<SelAllNoticeVo> voList = new ArrayList<>();
        SelAllNoticeVo vo = new SelAllNoticeVo();

        if(dto.getIlevel() == 1){
            voList = mapper.selAllNoticeBoardPar(dto);
        }
        if(dto.getIlevel() == 2 || dto.getIlevel() == 3){
            voList = mapper.selAllNoticeBoardTea(dto);
        }
        if(voList.size() == 0){
            vo.setResult(Const.NO_INFORMATION);
            voList.add(vo);
            return voList;
        }

        for(SelAllNoticeVo picCheck : voList){
            Integer result = mapper.selNoticeBoardPicCheck(picCheck.getInotice());
            if(result != null){
                picCheck.setPicCheck(Const.SUCCESS);
            }
        }

        return voList;
    }


}
