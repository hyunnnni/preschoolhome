package com.preschool.preschoolhome.kid;

import com.preschool.preschoolhome.common.exception.AuthErrorCode;
import com.preschool.preschoolhome.common.exception.CommonErrorCode;
import com.preschool.preschoolhome.common.exception.RestApiException;
import com.preschool.preschoolhome.common.security.AuthenticationFacade;
import com.preschool.preschoolhome.common.utils.Const;
import com.preschool.preschoolhome.common.utils.MyFileUtils;
import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.kid.model.*;
import com.preschool.preschoolhome.kid.model.KidDetailEditVo;
import com.preschool.preschoolhome.kid.model.KidGrowth;
import com.preschool.preschoolhome.kid.model.KidParent;
import com.preschool.preschoolhome.kid.model.KidProfileVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KidService {
    private final KidMapper mapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;

    //원아 해당 년도 마이페이지 조회
    public KidProfileVo kidProfile(int year, int ikid) {
        int level = authenticationFacade.getLevelPk();
        if (level < 1) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        try {
            KidProfileVo vo = mapper.kidProfile(ikid);
            vo.setResult(Const.SUCCESS);
            List<KidParent> parents = mapper.kidParent(ikid);
            List<KidGrowth> growths = mapper.kidGrowth(ikid, year);
            vo.setParents(parents);
            vo.setGrowths(growths);
            return vo;
        }catch (Exception e){
            e.printStackTrace();
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }


    }

    //원아 식별코드 수정
    ResVo kidCode(int ikid){
        try {
            mapper.kidCode(ikid);
            return new ResVo(Const.SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
    //원아 등록
    public KidInsVo kidSignup(MultipartFile pic, KidInsDto dto) {
        int level = authenticationFacade.getLevelPk();
        if (level < 2) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        if (dto.getKidNm() == null || dto.getBirth() == null ||
                dto.getAddress() == null || !(dto.getGender() == 0 || dto.getGender() == 1) ||
                pic == null) {
            throw new RestApiException(AuthErrorCode.ALL_YOU_NEED_IS_PARAM);
        }
        try {
            String path = "/kid/profile";
            String savedPicFileNm = myFileUtils.transferTo(pic, path);
            dto.setProfile(savedPicFileNm);
            mapper.kidSignup(dto);
            int ikid = mapper.selIkid(dto);
            dto.setIkid(ikid);
            mapper.kidgrade(dto);
            KidInsVo vo2 = new KidInsVo();
            vo2.setCode(dto.getCode());
            vo2.setIkid(ikid);
            return vo2;
        } catch (Exception e){
            e.printStackTrace();
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
    //원아 발달사항 등록
    public ResVo kidInsDetail(List<KidDetailInsDto> list) {
        int level = authenticationFacade.getLevelPk();
        if (level < 2 ) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        GrowhCheck vo = new GrowhCheck();
        for (KidDetailInsDto dto : list) {
            if (dto.getGrowthDate() != null) {
                if(dto.getGrowth() < 1 || dto.getGrowth() > 10 ){
                    throw new RestApiException(AuthErrorCode.NOT_EMPTY_INFO);
                }
                int growthmonth = Integer.parseInt(dto.getGrowthDate().substring(5, 7));
                switch (growthmonth / 3) {
                    case 0: dto.setGrowthQuarterly(4); break;
                    case 1: dto.setGrowthQuarterly(1); break;
                    case 2: dto.setGrowthQuarterly(2); break;
                    case 3: dto.setGrowthQuarterly(3); break;
                    case 4: dto.setGrowthQuarterly(4); break;
                }
                vo.setGrowthQuarterly(dto.getGrowthQuarterly());
                vo.setIkid(dto.getIkid());
                vo.setGrowthDate(dto.getGrowthDate());
                Integer check = mapper.growthCheck(vo);
                log.info("check:{}", check);
                if(check != null){
                    throw new RestApiException(AuthErrorCode.OVER_GROWTH);
                }
                try {
                    mapper.kidGrowthInsDetail(dto);
                } catch (Exception e){
                    e.printStackTrace();
                    throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
                }
            }
            if (dto.getBodyDate() != null) {
                if (dto.getHeight() < 1 || dto.getWeight() < 1) {
                    throw new RestApiException(AuthErrorCode.NOT_EMPTY_INFO);
                }
                int bodymonth = Integer.parseInt(dto.getBodyDate().substring(5, 7));
                switch (bodymonth / 3) {
                    case 0: dto.setBodyQuarterly(4); break;
                    case 1: dto.setBodyQuarterly(1); break;
                    case 2: dto.setBodyQuarterly(2); break;
                    case 3: dto.setBodyQuarterly(3); break;
                    case 4: dto.setBodyQuarterly(4); break;
                }
                vo.setBodyQuarterly(dto.getBodyQuarterly());
                vo.setIkid(dto.getIkid());
                vo.setBodyDate(dto.getBodyDate());
                Integer check = mapper.bodyCheck(vo);
                if(check != null){
                    throw new RestApiException(AuthErrorCode.OVER_GROWTH);
                }
                try {
                    mapper.kidBodyInsDetail(dto);
                } catch (Exception e){
                    e.printStackTrace();
                    throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
                }
            }
        }
        return new ResVo(Const.SUCCESS);
    }
    //원아 발달사항 수정
    ResVo kidUpdDetail(List<KidDetailUpdDto> list) {
        int level = authenticationFacade.getLevelPk();
        if (level < 2 ) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        for (KidDetailUpdDto dto : list) {
            if (dto.getGrowthDate() != null) {
                if(dto.getGrowth() < 1 || dto.getGrowth() > 10 ){
                    throw new RestApiException(AuthErrorCode.NOT_EMPTY_INFO);
                }
                int growthmonth = Integer.parseInt(dto.getGrowthDate().substring(5, 7));
                switch (growthmonth / 3) {
                    case 0: dto.setGrowthQuarterly(4); break;
                    case 1: dto.setGrowthQuarterly(1); break;
                    case 2: dto.setGrowthQuarterly(2); break;
                    case 3: dto.setGrowthQuarterly(3); break;
                    case 4: dto.setGrowthQuarterly(4); break;
                }
                try {
                    mapper.kidGrowthUpdDetail(dto);
                } catch (Exception e){
                    e.printStackTrace();
                    throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
                }

            }
            if (dto.getBodyDate() != null) {
                if (dto.getHeight() < 1 || dto.getWeight() < 1) {
                    throw new RestApiException(AuthErrorCode.NOT_EMPTY_INFO);
                }
                int bodymonth = Integer.parseInt(dto.getBodyDate().substring(5, 7));
                switch (bodymonth / 3) {
                    case 0: dto.setBodyQuarterly(4); break;
                    case 1: dto.setBodyQuarterly(1); break;
                    case 2: dto.setBodyQuarterly(2); break;
                    case 3: dto.setBodyQuarterly(3); break;
                    case 4: dto.setBodyQuarterly(4); break;
                }

                try {
                    mapper.kidBodyUpdDetail(dto);
                } catch (Exception e){
                    e.printStackTrace();
                    throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
                }
            }
        }
        return new ResVo(Const.SUCCESS);
    }
    //원아 발달사항 수정 시 기존 데이터 조회
    public KidDetailEditVo kidDetailEdit(int ikid, int year) {
        int level = authenticationFacade.getLevelPk();
        if (level < 2 ) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        try {
            KidDetailEditVo vo = mapper.kidDetailEdit(ikid);
            List<KidGrowth> growths = mapper.kidGrowth(ikid, year);
            vo.setGrowths(growths);
            vo.setResult(Const.SUCCESS);
            return vo;
        } catch (Exception e){
            e.printStackTrace();
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
    //원아 프로필 수정
    public ResVo kidUpdProfile(MultipartFile pic, KidUpdDto dto) {
        int level = authenticationFacade.getLevelPk();
        if (level < 2 ) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        if (dto.getKidNm() == null || dto.getBirth() == null ||
                dto.getAddress() == null || !(dto.getGender() == 0 || dto.getGender() == 1) ||
                pic == null) {
            throw new RestApiException(AuthErrorCode.NOT_EMPTY_INFO);
        }
        try {
            String path = "/kid/" + dto.getIkid();
            myFileUtils.delFolderTrigger(path);
            String savedPicFileNm = myFileUtils.transferTo(pic, path);
            dto.setProfile(savedPicFileNm);
            mapper.kidUpdProfile(dto);
            return new ResVo(Const.SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
    //원아 프로필 수정 시 기존 데이터 조회
    public KidProfileEditVo kidEdit(int ikid){
        int level = authenticationFacade.getLevelPk();
        if (level < 2 ) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        try {
            KidProfileEditVo vo = mapper.kidEdit(ikid);
            vo.setResult(Const.SUCCESS);
            return vo;
        } catch (Exception e){
            e.printStackTrace();
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //졸업한 지 10년 된  원아 전체 삭제
    public ResVo allGraduateKid() {
        int level = authenticationFacade.getLevelPk();
        if (level < 3 ) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        try {
            mapper.allGraduateKid();
            mapper.allGraduateDelKid();
            return new ResVo(Const.SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
