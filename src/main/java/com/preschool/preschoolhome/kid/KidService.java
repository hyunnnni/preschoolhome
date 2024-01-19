package com.preschool.preschoolhome.kid;

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

    //원아 해당 년도 마이페이지 조회
    public KidProfileVo kidProfile(int year, int ikid, int ilevel) {
        KidProfileVo vo = new KidProfileVo();
        if (ilevel < 2) {
            vo.setResult(Const.FAIL);
            return vo;
        }
        vo = mapper.kidProfile(ikid);
        vo.setResult(Const.SUCCESS);
        List<KidParent> parents = mapper.kidParent(ikid);
        List<KidGrowth> growths = mapper.kidGrowth(ikid, year);
        vo.setGrowths(growths);
        vo.setParents(parents);
        return vo;
    }

    //원아 식별코드 수정
    ResVo kidCode(int ikid, int ilevel){
        if(ilevel<2){
            return new ResVo(Const.FAIL);
        }
        mapper.kidCode(ikid);
        return new ResVo(Const.SUCCESS);
    }
    //원아 등록
    public KidInsVo kidSignup(MultipartFile pic, KidInsDto dto) {
        if (dto.getKidNm() == null || dto.getBirth() == null ||
                dto.getAddress() == null || !(dto.getGender() == 0 || dto.getGender() == 1) ||
                pic == null || dto.getIlevel() < 2) {
            KidInsVo vo1 = new KidInsVo();
            vo1.setIkid(Const.FAIL);
            return vo1;
        }
        String path = "/kid/profile";
        String savedPicFileNm = myFileUtils.transferTo(pic, path);
        dto.setProfile(savedPicFileNm);
        mapper.kidSignup(dto);
        int ikid = mapper.selIkid(dto);
        KidInsVo vo2 = new KidInsVo();
        vo2.setCode(dto.getCode());
        vo2.setIkid(ikid);
        return vo2;
    }
    //원아 발달사항 등록
    public ResVo kidInsDetail(List<KidDetailInsDto> list) {
        for (KidDetailInsDto dto : list) {

            if (dto.getGrowthDate() != null) {
                if (dto.getIlevel() < 2 || dto.getGrowth() < 1) {
                    return new ResVo(Const.FAIL);
                }
                int growthmonth = Integer.parseInt(dto.getGrowthDate().substring(5, 7));
                switch (growthmonth / 3) {
                    case 0: dto.setGrowthQuarterly(4); break;
                    case 1: dto.setGrowthQuarterly(1); break;
                    case 2: dto.setGrowthQuarterly(2); break;
                    case 3: dto.setGrowthQuarterly(3); break;
                    case 4: dto.setGrowthQuarterly(4); break;
                }
                mapper.kidGrowthInsDetail(dto);
            }
            if (dto.getBodyDate() != null) {
                if (dto.getIlevel() < 2 || dto.getHeight() < 1 || dto.getWeight() < 1) {
                    return new ResVo(Const.FAIL);
                }
                int bodymonth = Integer.parseInt(dto.getBodyDate().substring(5, 7));
                switch (bodymonth / 3) {
                    case 0: dto.setBodyQuarterly(4); break;
                    case 1: dto.setBodyQuarterly(1); break;
                    case 2: dto.setBodyQuarterly(2); break;
                    case 3: dto.setBodyQuarterly(3); break;
                    case 4: dto.setBodyQuarterly(4); break;
                }
                mapper.kidBodyInsDetail(dto);
            }
            //같은 분기가 들어오지 않는 작업 필요

        }
        return new ResVo(Const.SUCCESS);
    }
    //원아 발달사항 수정
    ResVo kidUpdDetail(List<KidDetailUpdDto> list) {
        for (KidDetailUpdDto dto : list) {
            if (dto.getGrowthDate() != null) {
                if (dto.getIlevel() < 2 || dto.getGrowth() < 1) {
                    return new ResVo(Const.FAIL);
                }
                int growthmonth = Integer.parseInt(dto.getGrowthDate().substring(5, 7));
                switch (growthmonth / 3) {
                    case 0: dto.setGrowthQuarterly(4); break;
                    case 1: dto.setGrowthQuarterly(1); break;
                    case 2: dto.setGrowthQuarterly(2); break;
                    case 3: dto.setGrowthQuarterly(3); break;
                    case 4: dto.setGrowthQuarterly(4); break;
                }
                mapper.kidGrowthUpdDetail(dto);
            }
            if (dto.getBodyDate() != null) {
                if (dto.getIlevel() < 2 || dto.getHeight() < 1 || dto.getWeight() < 1) {
                    return new ResVo(Const.FAIL);
                }
                int bodymonth = Integer.parseInt(dto.getBodyDate().substring(5, 7));
                switch (bodymonth / 3) {
                    case 0: dto.setBodyQuarterly(4); break;
                    case 1: dto.setBodyQuarterly(1); break;
                    case 2: dto.setBodyQuarterly(2); break;
                    case 3: dto.setBodyQuarterly(3); break;
                    case 4: dto.setBodyQuarterly(4); break;
                }
                mapper.kidBodyUpdDetail(dto);
            }
        }
        return new ResVo(Const.SUCCESS);
    }
    //원아 발달사항 수정 시 기존 데이터 조회
    public KidDetailEditVo kidDetailEdit(int ikid, int ilevel, int year) {
        KidDetailEditVo vo = new KidDetailEditVo();
        if (ilevel < 2) {
            vo.setResult(Const.FAIL);
            return vo;
        }
        vo = mapper.kidDetailEdit(ikid);
        List<KidGrowth> growths = mapper.kidGrowth(ikid, year);
        vo.setGrowths(growths);
        vo.setResult(Const.SUCCESS);
        return vo;
    }
    //원아 프로필 수정
    public ResVo kidUpdProfile(MultipartFile pic, KidUpdDto dto) {
        if (dto.getKidNm() == null || dto.getBirth() == null ||
                dto.getAddress() == null || !(dto.getGender() == 0 || dto.getGender() == 1) ||
                pic == null || dto.getIlevel() < 2) {
            ResVo vo1 = new ResVo(Const.FAIL);
            return vo1;
        }
        String path = "/kid/" + dto.getIkid();
        myFileUtils.delFolderTrigger(path);
        String savedPicFileNm = myFileUtils.transferTo(pic, path);
        dto.setProfile(savedPicFileNm);
        mapper.kidUpdProfile(dto);
        return new ResVo(Const.SUCCESS);
    }
    //원아 프로필 수정 시 기존 데이터 조회
    public KidProfileEditVo kidEdit(int ikid, int ilevel){
        KidProfileEditVo vo = new KidProfileEditVo();
        if (ilevel < 2) {
            vo.setResult(Const.FAIL);
            return vo;
        }
        vo = mapper.kidEdit(ikid);
        vo.setResult(Const.SUCCESS);
        return vo;
    }

    //졸업한 지 10년 된  원아 전체 삭제
    public ResVo allGraduateKid(int ilevel) {
        if (ilevel < 3) {
            return new ResVo(Const.FAIL);
        }
        mapper.allGraduateKid();
        mapper.allGraduateDelKid();
        return new ResVo(Const.SUCCESS);
    }

}
