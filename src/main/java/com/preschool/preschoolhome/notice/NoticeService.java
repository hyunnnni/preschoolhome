package com.preschool.preschoolhome.notice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.preschool.preschoolhome.common.exception.AuthErrorCode;
import com.preschool.preschoolhome.common.exception.RestApiException;
import com.preschool.preschoolhome.common.security.AuthenticationFacade;
import com.preschool.preschoolhome.common.utils.Const;
import com.preschool.preschoolhome.common.utils.MyFileUtils;
import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.common.utils.ResVoArray;
import com.preschool.preschoolhome.notice.model.*;
import com.preschool.preschoolhome.notice.model.NoticeUpdSelVo;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeService {
    private final NoticeMapper mapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;
    private final ObjectMapper objMapper;


    //-------------------------------- 알림장 등록 --------------------------------
    public ResVoArray insNotice(List<MultipartFile> pics, NoticeInsDto dto) {

        int writerIuser = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        String loginUserNm = authenticationFacade.getUserNm();
        if (level > Const.BOSS) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }

        NoticeInsProcDto pdto = NoticeInsProcDto.builder()
                .writerIuser(writerIuser)
                .writerIlevel(level)
                .noticeTitle(dto.getNoticeTitle())
                .noticeContents(dto.getNoticeContents())
                .noticeCheck(dto.getNoticeCheck())
                .build();

        int result;

        List<Integer> inotices = new ArrayList<>();
        for(Integer kid : dto.getIkids()){
            pdto.setIkid(kid);
            result = mapper.insNotice(pdto);
            if (result == 0) {
                throw new RestApiException(AuthErrorCode.FAIL);
            }
            inotices.add(pdto.getInotice());
        }

        try {
            if (pics != null && pics.size() <= Const.NOTICE_PIC) {
                NoticePicsInsDto picsDto = new NoticePicsInsDto();
                List<File> originFile = new ArrayList<>();


                for (int i = 0; i < inotices.size(); i++) {
                    String target = "/notice/" + inotices.get(i);
                    picsDto.setInotice(inotices.get(i));

                    if (i == 0) {
                        for (MultipartFile file : pics) {
                            String fileNm = myFileUtils.getRandomFileNm(file);
                            File saveFile = new File(myFileUtils.makeFolders(target), fileNm);
                            File subFile = new File(myFileUtils.makeFolders("/notice/sub"), fileNm);

                            file.transferTo(saveFile);
                            Files.copy(saveFile.toPath(), subFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                            originFile.add(subFile);
                            picsDto.getPics().add(fileNm);
                        }
                    } else {
                        String folderPath = myFileUtils.makeFolders(target);
                        for (int j = 0; j < originFile.size(); j++) {
                            File copyFile = new File(folderPath, originFile.get(j).getName());
                            Files.copy(originFile.get(j).toPath(), copyFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            picsDto.getPics().add(originFile.get(j).getName());
                        }
                    }
                    mapper.insNoticePics(picsDto);
                    picsDto.getPics().clear();
                }
                myFileUtils.delFolderTrigger("/notice/sub");
            }
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.PICS_FAIL);
        }

        LocalDateTime now = LocalDateTime.now(); // 현재 날짜 구하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 포맷 정의
        String createdAt = now.format(formatter); // 포맷 적용

        List<SelNoticeOtherTokens> otherTokens = new ArrayList<>();

        if(level == Const.PARENT) {
            otherTokens = mapper.selTeaFirebaseByLoginUser(inotices, dto.getIkids());
        }
        if(level == Const.TEACHER || level == Const.BOSS) {
            otherTokens = mapper.selParFirebaseByLoginUser(inotices);
        }

        if (otherTokens != null) {
            try {

                otherTokens.removeAll(Collections.singletonList(null));
                for (SelNoticeOtherTokens token : otherTokens) {

                    NoticePushVo pushVo = new NoticePushVo();
                    pushVo.setNoticeTitle(dto.getNoticeTitle());
                    pushVo.setWriterIuser(writerIuser);
                    pushVo.setInotice(token.getInotice());
                    pushVo.setIkid(token.getIkid());
                    pushVo.setKidNm(token.getKidNm());
                    pushVo.setCreatedAt(createdAt);

                    String body = objMapper.writeValueAsString(pushVo);
                    log.info("body: {}", body);
                    Notification noti = Notification.builder()
                            .setTitle("postNotice")
                            .setBody(body)
                            .build();

                    Message message = Message.builder()
                            .setToken(token.getFirebaseToken())
                            .setNotification(noti)
                            .build();

                    FirebaseMessaging.getInstance().sendAsync(message);
                }
            } catch (Exception e) {
                throw new RestApiException(AuthErrorCode.PUSH_FAIL);
            }
        }
        return new ResVoArray(inotices);

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
        List<NoticePicsVo> pics = mapper.noticeEditPics(inotice);
        if (pics == null) {
            throw new RestApiException(AuthErrorCode.PICS_FAIL);
        }
        vo.setNoticePics(pics);
        return vo;
    }

    //-------------------------------- 알림장 수정 --------------------------------
    @Transactional
    public ResVo updNotice(List<MultipartFile> pics, NoticeUpdDto dto) {
        int iteacher = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        dto.setIteacher(iteacher);
        if (level < 2) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }

        int affectedRows = mapper.updNotice(dto);
        if (affectedRows == 0) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }

        if (dto.getDelPic() != null) {
            int affectedDelRows = mapper.delNoticePics(dto.getDelPic());
            if (affectedDelRows == 0) {
                throw new RestApiException(AuthErrorCode.FAIL);
            }
        }
        if (pics == null) {
            return new ResVo(dto.getInotice());

        }
        String target = "/notice/" + dto.getInotice();

        NoticePicsInsDto picsDto = new NoticePicsInsDto();
        picsDto.setInotice(dto.getInotice());
        List<Integer> selpics = mapper.noticeSelPics(dto.getInotice());
        if(Const.NOTICE_PIC - selpics.size() < pics.size() ){
            throw new RestApiException(AuthErrorCode.PICS_FAIL);
        }

        for (MultipartFile file : pics) {
            String saveFileNm = myFileUtils.transferTo(file, target);
            picsDto.getPics().add(saveFileNm);
        }
        int affectedPicRows = mapper.insNoticePics(picsDto);
        if (affectedPicRows == 0) {
            throw new RestApiException(AuthErrorCode.PICS_FAIL);
        }

        return new ResVo(dto.getInotice());
    }

    //-------------------------------- 알림장 삭제 --------------------------------
    @Transactional
    public ResVo delNotice(int inotice) {
        int level = authenticationFacade.getLevelPk();

        if (level < 2) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        int result = mapper.delAllNotice(inotice);
        int result2 = mapper.delNotice(inotice);
        if (result2 == 0) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }
        return new ResVo(Const.SUCCESS);
    }

    //-------------------------------- 알림장 접근 유저에 따라 다르게 전체 조회 --------------------------------
    public AllNoticeVo getKidManagement(SelAllNoticeDto dto) {
        int iuser = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        dto.setLoginedIuser(iuser);
        dto.setIlevel(level);

        List<SelAllNoticeVo> voList = new ArrayList<>();
        int noticeCnt = 0;
        if (dto.getIlevel() == 1) {
            voList = mapper.selAllNoticeBoardPar(dto);
            noticeCnt = mapper.selNoticeParCnt(dto);
        }
        if (dto.getIlevel() == 2 || dto.getIlevel() == 3) {
            voList = mapper.selAllNoticeBoardTea(dto);
            noticeCnt = mapper.selNoticeTeaCnt(dto);
        }
        if (voList.size() == 0) {
            throw new RestApiException(AuthErrorCode.NO_INFORMATION);
        }

        for (SelAllNoticeVo check : voList) {
            Integer result1 = mapper.selNoticeBoardPicCheck(check.getInotice());
            Integer result2 = mapper.selNoticeBoardCmtCheck(check.getInotice());

            if (result1 != null && result1 > 0) {
                check.setPicCheck(Const.SUCCESS);
            }
            if(result2 != null && result2 > 0){
                check.setCmtCheck(Const.SUCCESS);
            }
        }
        AllNoticeVo vo = new AllNoticeVo();
        vo.setList(voList);

        vo.setNoticeCnt(noticeCnt);
        return vo;
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
        if ((dto.getIparent() == 0 && dto.getIteacher() == 0) ||
                (dto.getIteacher() > 0 && dto.getIparent() > 0)) {
            throw new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION);
        }
        int level = authenticationFacade.getLevelPk();
        int writerIuser = authenticationFacade.getLoginUserPk();
        dto.setIlevel(level);

        int result = mapper.insNoticeComment(dto);
        if (result == 0) {
            return new ResVo(Const.FAIL);
        }

        LocalDateTime now = LocalDateTime.now(); // 현재 날짜 구하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 포맷 정의
        String createdAt = now.format(formatter); // 포맷 적용


        List<String> otherTokens = null;
        if(level == Const.PARENT) {
            otherTokens = mapper.selTeaFirebaseByLoginUserComment(dto.getInoticeComment());
        }
        if(level == Const.TEACHER || level == Const.BOSS) {
            otherTokens = mapper.selParFirebaseByLoginUserComment(dto.getInoticeComment());
        }
        try {

            if (otherTokens != null) {
                otherTokens.removeAll(Collections.singletonList(null));
                for (String token : otherTokens) {

                    NoticeCommentPushVo pushVo = new NoticeCommentPushVo();
                    pushVo.setNoticeComment(dto.getNoticeComment());
                    pushVo.setWriterIuser(writerIuser);
                    pushVo.setCreatedAt(createdAt);

                    String body = objMapper.writeValueAsString(pushVo);
                    log.info("body: {}", body);
                    Notification noti = Notification.builder()
                            .setTitle("dm")
                            .setBody(body)
                            .build();

                    Message message = Message.builder()
                            .setToken(token)
                            .setNotification(noti)
                            .build();

                    FirebaseMessaging.getInstance().sendAsync(message);
                }
            }
            } catch(Exception e){
                throw new RestApiException(AuthErrorCode.PUSH_FAIL);
            }
            return new ResVo(result);

    }

    //-------------------------------- 알림장 댓글 삭제 --------------------------------
    public ResVo delNoticeComment(DelNoticeCommentDto dto) {

        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        if ((dto.getIparent() == 0 && dto.getIteacher() == 0) ||
                (dto.getIparent() > 0 && dto.getIteacher() > 0)) {
            throw new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION);
        }

        int result = mapper.delNoticeComment(dto);

        if (result == 0) {
            return new ResVo(Const.NO_INFORMATION);
        }
        return new ResVo(result);
    }

    //-------------------------------- 알림장 작성 시 원아 태그에 쓰일 모든 원아 정보 --------------------------------
    public List<SelTagKids> getFromKids(){
        return mapper.selFromKids();
    }
}
