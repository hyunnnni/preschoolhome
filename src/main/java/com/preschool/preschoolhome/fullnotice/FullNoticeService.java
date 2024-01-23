package com.preschool.preschoolhome.fullnotice;

import com.preschool.preschoolhome.common.exception.AuthErrorCode;
import com.preschool.preschoolhome.common.exception.RestApiException;
import com.preschool.preschoolhome.common.security.AuthenticationFacade;
import com.preschool.preschoolhome.common.utils.Const;
import com.preschool.preschoolhome.common.utils.MyFileUtils;
import com.preschool.preschoolhome.fullnotice.model.SelFullNoticeDto;
import com.preschool.preschoolhome.fullnotice.model.SelFullNoticeVo;
import com.preschool.preschoolhome.fullnotice.model.SelNoticeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FullNoticeService {
    private final FullNoticeMapper mapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;

    //-------------------게시판 조회-----------
    //전체게시판 조회
    public List<SelFullNoticeVo> getAllFullNotice(SelFullNoticeDto dto) {
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        List<SelFullNoticeVo> noticeFix = mapper.getFullNoticeFix();
        if (noticeFix.size() == 0 || noticeFix.size()>3) {
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
        if(voList.size()>10){
            throw new RestApiException(AuthErrorCode.OVER_FIX_NOTICE);
        }

        return voList;
    }

    //우선 받은값들을 가져와서 사진여러개를 공지사항 안에 넣어줘야함
    //개별게시판 조회
    public SelNoticeVo getFullNotice(int iFullNotice) {
        SelNoticeVo notice = mapper.getNotice(iFullNotice);
        List<String> pics = mapper.selNoticePic(iFullNotice);
        notice.setPics(pics);

        return notice;
    }

}
