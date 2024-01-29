package com.preschool.preschoolhome.notice;

import com.preschool.preschoolhome.common.exception.AuthErrorCode;
import com.preschool.preschoolhome.common.exception.CommonErrorCode;
import com.preschool.preschoolhome.common.exception.RestApiException;
import com.preschool.preschoolhome.common.security.AuthenticationFacade;
import com.preschool.preschoolhome.common.utils.Const;
import com.preschool.preschoolhome.common.utils.MyFileUtils;
import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.notice.model.*;
import com.preschool.preschoolhome.notice.model.NoticeUpdSelVo;
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

    //-------------------------------- 알림장 등록 --------------------------------

    ResVo insNotice(List<MultipartFile> pics, NoticeInsDto dto) {
        int iteacher = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        dto.setIteacher(iteacher);
        if (level < 2) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }

        int result = mapper.insNotice(dto);
        if (result == 0) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }
        String target = "/notice/" + dto.getInotice();

        if (pics != null) {
            NoticePicsInsDto picsDto = new NoticePicsInsDto();
            picsDto.setInotice(dto.getInotice());
            for (MultipartFile file : pics) {
                String saveFileNm = myFileUtils.transferTo(file, target);
                picsDto.getPics().add(saveFileNm);
            }
            int result2 = mapper.insNoticePics(picsDto);
            if (result2 == 0) {
                throw new RestApiException(AuthErrorCode.PICS_FAIL);
            }
        }
        return new ResVo(Const.SUCCESS);
    }

    //-------------------------------- 알림장 수정 시 정보 출력--------------------------------

    public NoticeUpdSelVo noticeEdit(int inotice, int ikid) {
        int level = authenticationFacade.getLevelPk();
        if (level < 2) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }

        NoticeUpdSelVo vo = mapper.noticeEdit(inotice, ikid);
        if (vo == null) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }
        List<String> pics = mapper.noticeEditPics(inotice);
        if (pics == null) {
            throw new RestApiException(AuthErrorCode.PICS_FAIL);
        }
        vo.setNoticePics(pics);
        return vo;
    }


    //-------------------------------- 알림장 수정 --------------------------------

    public ResVo updNotice(List<MultipartFile> pics, NoticeUpdDto dto) {
        int iteacher = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        dto.setIteacher(iteacher);
        if (level < 2) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }

        String target = "/notice/" + dto.getInotice();
        int affectedRows = mapper.updNotice(dto);

        int affectedDelRows = mapper.delNoticePics(dto.getInotice());

        if (pics != null) {
            NoticePicsInsDto picsDto = new NoticePicsInsDto();
            picsDto.setInotice(dto.getInotice());
            for (MultipartFile file : pics) {
                String saveFileNm = myFileUtils.transferTo(file, target);
                picsDto.getPics().add(saveFileNm);
            }
            int affectedPicRows = mapper.insNoticePics(picsDto);
            if (affectedPicRows == 0) {
                throw new RestApiException(AuthErrorCode.PICS_FAIL);
            }
        }
        return new ResVo(Const.SUCCESS);
    }

    //-------------------------------- 알림장 삭제 --------------------------------

    ResVo delNotice(int inotice) {
        int iteacher = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();

        if (level != 2) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        int result = mapper.delAllNotice(iteacher, inotice);
        int result2 = mapper.delNotice(iteacher, inotice);
        if (result2 == 0) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }
        return new ResVo(Const.SUCCESS);
    }

    //-------------------------------- 알림장 접근 유저에 따라 다르게 전체 조회 --------------------------------
    public List<SelAllNoticeVo> getKidManagement(SelAllNoticeDto dto) {
        int iuser = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        dto.setLoginedIuser(iuser);
        dto.setIlevel(level);

        List<SelAllNoticeVo> voList = new ArrayList<>();
        SelAllNoticeVo vo = new SelAllNoticeVo();

        if (dto.getIlevel() == 1) {
            voList = mapper.selAllNoticeBoardPar(dto);
        }
        if (dto.getIlevel() == 2 || dto.getIlevel() == 3) {
            voList = mapper.selAllNoticeBoardTea(dto);
        }
        if (voList.size() == 0) {
            throw new RestApiException(AuthErrorCode.NO_INFORMATION);
        }

        for (SelAllNoticeVo picCheck : voList) {
            Integer result = mapper.selNoticeBoardPicCheck(picCheck.getInotice());
            if (result != null) {
                picCheck.setPicCheck(Const.SUCCESS);
            }
        }

        return voList;

    }

    //-------------------------------- 알림장 상세 조회 --------------------------------
    public SelDetailNoticeVo getDetailNotice(SelDetailNoticeDto dto) {

        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        SelDetailNoticeVo vo = mapper.selNoticeDetail(dto.getInotice());

        if (vo == null) {
            throw new RestApiException(AuthErrorCode.NO_INFORMATION);
        }

        vo.setPics(mapper.selNoticeDetailPics(dto.getInotice()));

        List<SelNoticeComment> comList = mapper.selNoticeDetailCom(dto.getInotice());
        List<SelNoticeCommentProc> comments = new ArrayList<>();

        if (comList.size() > 0) {
            for (SelNoticeComment com : comList) {
                if (com.getIparent() > 0 && com.getIteacher() == 0) {
                    SelNoticeCommentProc parComment = mapper.selNoticeDetailPar(com.getIparent());
                    parComment.setInoticeComment(com.getInoticeComment());
                    parComment.setNoticeComment(com.getNoticeComment());
                    parComment.setCreatedAt(com.getCreatedAt());
                    comments.add(parComment);
                }
                if (com.getIteacher() > 0 && com.getIparent() == 0) {
                    SelNoticeCommentProc teaComment = mapper.selNoticeDetailTea(com.getIteacher());
                    teaComment.setInoticeComment(com.getInoticeComment());
                    teaComment.setNoticeComment(com.getNoticeComment());
                    teaComment.setCreatedAt(com.getCreatedAt());
                    comments.add(teaComment);
                }

            }
        }
        vo.setComments(comments);

        return vo;
    }

    //-------------------------------- 알림장 댓글 등록 --------------------------------
    public ResVo postNoticeComment(InsNoticeCommentDto dto) {

        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        int result = mapper.insNoticeComment(dto);
        if (result == 0) {
            return new ResVo(Const.FAIL);
        }
        return new ResVo(result);

    }

    //-------------------------------- 알림장 댓글 삭제 --------------------------------
    public ResVo delNoticeComment(DelNoticeCommentDto dto) {

        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        if ((dto.getIparent() == 0 && dto.getIteacher() == 0) ||
                (dto.getIparent() > 0 && dto.getIteacher() > 0)) {
            return new ResVo(Const.BAD_PARAMETER);
        }

        int result = mapper.delNoticeComment(dto);

        if (result == 0) {
            return new ResVo(Const.NO_INFORMATION);
        }

        return new ResVo(result);
    }
}
