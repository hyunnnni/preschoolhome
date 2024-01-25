package com.preschool.preschoolhome.fullnotice;

import com.preschool.preschoolhome.common.exception.AuthErrorCode;
import com.preschool.preschoolhome.common.exception.CommonErrorCode;
import com.preschool.preschoolhome.common.exception.RestApiException;
import com.preschool.preschoolhome.common.security.AuthenticationFacade;
import com.preschool.preschoolhome.common.utils.Const;
import com.preschool.preschoolhome.common.utils.MyFileUtils;
import com.preschool.preschoolhome.fullnotice.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.preschool.preschoolhome.common.utils.ResVo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FullNoticeService {
    private final FullNoticeMapper mapper;
    private final MyFileUtils mfu;
    private final AuthenticationFacade authenticationFacade;

    //-------------------게시판 조회-----------
    //전체게시판 조회
    public List<SelFullNoticeVo> getAllFullNotice(SelFullNoticeDto dto) {
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        List<SelFullNoticeVo> noticeFix = mapper.getFullNoticeFix();
        if (noticeFix.size() == 0 || noticeFix.size() > 3) {
            throw new RestApiException(AuthErrorCode.NOT_IMPORTED);
        }

        dto.setRow(Const.NOTICE_COUNT_PER_PAGE - noticeFix.size());

        //size 0일때 예외처리, 두개값 합쳤을시 10 초과나 미만일시 예외처리
        List<SelFullNoticeVo> noticelist = mapper.getFullNoticelist(dto);
        if (noticelist.size() == 0) {
            throw new RestApiException(AuthErrorCode.NOT_IMPORTED);
        }
        List<SelFullNoticeVo> voList = new ArrayList<>();
        voList.addAll(noticeFix);
        voList.addAll(noticelist);
        if (voList.size() > 10) {
            throw new RestApiException(AuthErrorCode.OVER_FIX_NOTICE);
        }

        return voList;
    }

    //우선 받은값들을 가져와서 사진여러개를 공지사항 안에 넣어줘야함
    //개별게시판 조회
    public SelNoticeVo getFullNotice(int iFullNotice) {


        SelNoticeVo notice = mapper.getNotice(iFullNotice);
        if(notice == null){
            throw new RestApiException(AuthErrorCode.NOT_IMPORTED);
        }

        List<String> pics = mapper.selNoticePic(iFullNotice);
        notice.setPics(pics);
        return notice;
    }


//-------------------------------- 유치원 소식 작성 --------------------------------

    public ResVo postFullNotice (InsFullNoticeDto dto){

            int level = authenticationFacade.getLevelPk();
            dto.setIlevel(level);

            if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
                throw new RestApiException(AuthErrorCode.NO_PERMISSION);
            }

            Integer fix = mapper.selNoticeFix(dto.getIfullNotice());

            if (fix >= 3 && dto.getFullNoticeFix() == 1) {
                throw new RestApiException(AuthErrorCode.OVER_FIX_NOTICE);
            }

            int result = mapper.insFullNotice(dto);
            InsFullPicsDto pdto = new InsFullPicsDto();

            if (result == 0) {
                throw new RestApiException(AuthErrorCode.FAIL);
            }
            if (dto.getFullPic() == null) {
                return new ResVo(Const.SUCCESS);
            }
            pdto.setIfullNotice(dto.getIfullNotice());
            String target = "/fullnotice/" + dto.getIfullNotice();

            for (MultipartFile file : dto.getFullPic()) {
                String saverFileNm = mfu.transferTo(file, target);
                pdto.getFullPic().add(saverFileNm);
            }

            int picResult = mapper.insFullNoticePics(pdto);

            if (picResult < 1) {
                throw new RestApiException(AuthErrorCode.PICS_FAIL);
            }
            return new ResVo(dto.getIfullNotice());


    }

//-------------------------------- 유치원 소식 삭제 --------------------------------

    public ResVo delFullNotice(DelFullNoticeDto dto){

        try {
            int level = authenticationFacade.getLevelPk();
            dto.setIlevel(level);

            if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
                throw new RestApiException(AuthErrorCode.NO_PERMISSION);
            }

            if (dto.getIlevel() == 2) {
                Integer writer = mapper.selFullNoticeWriter(dto.getIfullNotice());

                if (writer == null || writer != dto.getIteacher()) {
                    throw new RestApiException(CommonErrorCode.INVALID_PARAMETER);
                }
            }
            int result = mapper.delFullNoticePics(dto);

            if (result == 0) {
                throw new RestApiException(AuthErrorCode.PICS_FAIL);
            }

            int result1 = mapper.delFullNotice(dto);

            if (result1 == 0) {
                throw new RestApiException(AuthErrorCode.FAIL);
            }
            return new ResVo(Const.SUCCESS);

        }catch (Exception e){
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

//-------------------------------- 유치원 소식 수정 --------------------------------

    public ResVo putFullNotice(UpdFullNoticeDto dto){

        try {
            int level = authenticationFacade.getLevelPk();
            dto.setIlevel(level);

            if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
                throw new RestApiException(AuthErrorCode.NO_PERMISSION);
            }

            Integer fix = mapper.selNoticeFix(dto.getIfullNotice());

            if (fix >= 3 && dto.getFullNoticeFix() == 1) {
                throw new RestApiException(AuthErrorCode.OVER_FIX_NOTICE);
            }

            int result = mapper.putFullNotice(dto);
            Integer selResult = mapper.selFullNoticePics(dto.getIfullNotice());
            InsFullPicsDto pdto = new InsFullPicsDto();

            if (result == 0) {
                throw new RestApiException(AuthErrorCode.FAIL);
            }
            if (dto.getFullPic() == null) {
                return new ResVo(Const.SUCCESS);
            }
            if (selResult > 0) {
                int delResult = mapper.delUpdFullNoticePics(dto.getIfullNotice());
                if (delResult == 0) {
                    throw new RestApiException(AuthErrorCode.PICS_FAIL);
                }
            }
            pdto.setIfullNotice(dto.getIfullNotice());
            String target = "/fullnotice/" + dto.getIfullNotice();

            for (MultipartFile file : dto.getFullPic()) {
                String saverFileNm = mfu.transferTo(file, target);
                pdto.getFullPic().add(saverFileNm);
            }

            int picResult = mapper.insFullNoticePics(pdto);

            if (picResult < 1) {
                throw new RestApiException(AuthErrorCode.PICS_FAIL);
            }
            return new ResVo(dto.getIfullNotice());

        }catch (Exception e){
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }

    }
//-------------------------------- 유치원 소식 수정 시 불러오기 --------------------------------

    public SelFullNoticeUpdVo getFullNoticeUpd(SelFullNoticeUpdDto dto){

        try {
            int level = authenticationFacade.getLevelPk();
            dto.setIlevel(level);

            if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
                throw new RestApiException(AuthErrorCode.NO_PERMISSION);
            }

            SelFullNoticeUpdVo vo = mapper.selFullNoticeUpd(dto.getIfullNotice());

            if (vo == null) {
                throw new RestApiException(AuthErrorCode.NO_INFORMATION);
            }

            List<String> picList = mapper.selFullNoticeUpdPics(dto.getIfullNotice());

            vo.setFullPic(picList);

            return vo;

        }catch (Exception e){
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }

    }
}
