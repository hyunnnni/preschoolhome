package com.preschool.preschoolhome.teacher;

import com.preschool.preschoolhome.common.exception.PreschoolErrorCode;
import com.preschool.preschoolhome.common.exception.RestApiException;
import com.preschool.preschoolhome.common.utils.MyFileUtils;
import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.common.utils.Const;
import com.preschool.preschoolhome.teacher.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherMapper mapper;
    private final MyFileUtils myFileUtils;

    //-------------------------------- 원아 관리 페이지 조회 --------------------------------
    public List<SelKidManagementVo> getKidManagement(SelKidManagementDto dto){

        List<SelKidManagementVo> voList = new ArrayList<>();
        SelKidManagementVo vo = new SelKidManagementVo();

        if(!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)){
            vo.setResult(Const.NO_PERMISSION);
            voList.add(vo);
            return voList;
        }

        voList = mapper.selKidManagement(dto);

        if(voList.size() > 0){
            return voList;
        }

        vo.setResult(Const.NO_INFORMATION);
        voList.add(vo);
        return voList;


    }
    //-------------------------------- 원아 재원 상태 / 반 승급 수정 --------------------------------
    public ResVo patchKidStateOrClass (UpdKidStateDto dto){

        if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
            return new ResVo(Const.NO_PERMISSION);
        }

        int result = mapper.updKidStateOrClass(dto);
        DelStateParentIsProc pDto = new DelStateParentIsProc();
        int delResult = Const.RESULT;

        if(dto.getKidCheck() == Const.STATE_GRADUATE || dto.getKidCheck() == Const.STATE_DROP_OUT) {

            List<Integer> iParents = mapper.SelGraduateParentPk(dto.getIkids());

            if (iParents.size() != 0) {
                for (int parent : iParents) {

                    List<Integer> ikids = mapper.SelGraduateKidPk(parent);
                    List<Integer> state = mapper.selStateKid(ikids);

                    if (state.contains(Const.ZERO)) {
                        continue;
                    }
                    pDto.setIparent(parent);
                    delResult = mapper.updStateIsDelParent(pDto);
                    if (delResult == Const.ZERO) {
                        return new ResVo(Const.UPD_IS_DEL_FAIL);
                    }
                }
            }
        }
        if (result > Const.SUCCESS || result > Const.ZERO) {
            return new ResVo(result);
        }
        if (result == Const.ZERO){
            return new ResVo(Const.UPD_STATE_FAIL);
        }
        return new ResVo(Const.FAIL);
    }
    //-------------------------------- 학부모 관리 페이지 조회 --------------------------------
    public List<SelParManagementVo> getParentManagement (SelParManagementDto dto){
        List<SelParManagementVo> voList = new ArrayList<>();
        SelParManagementVo vo = new SelParManagementVo();

        if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
            vo.setResult(Const.NO_PERMISSION);
            voList.add(vo);
            return voList;
        }

        if(dto.getIclass() > 0){
            voList = mapper.selParManagementClass(dto);
        }else if(dto.getIclass() == 0) {
            voList = mapper.selParManagement(dto);
        }

        if(voList.size() == 0){
            vo.setResult(Const.NO_INFORMATION);
            voList.add(vo);
            return voList;
        }

        for(SelParManagementVo vo1 : voList){
            List<SelKidNameClass> kids = mapper.selConnectionKid(vo1.getIparent());
            if(kids.size() > 0) {
                vo1.setKids(kids);
            }
        }

        return voList;
    }

//-------------------------------- 학부모 정보 관리자가 삭제 --------------------------------

    public ResVo delParent(DelParentDto dto){

        if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
            return new ResVo(Const.NO_PERMISSION);
        }

        int delResult = mapper.delParDisconnectKid(dto.getIparents());
        if (delResult == 0){
            return new ResVo(Const.UPD_IS_DEL_FAIL);
        }
        int isDelResult = mapper.updIsDelParent(dto);
        if (isDelResult == 0){
            return new ResVo(Const.UPD_IS_DEL_FAIL);
        }

        return new ResVo(isDelResult);
    }

//-------------------------------- 학부모와 원아 연결 끊기  --------------------------------

    public ResVo delDisconnect(DelDisconnectDto dto){

        if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
            return new ResVo(Const.NO_PERMISSION);
        }

        int result = mapper.delDisconnent(dto);

        if(result == 0){
            return new ResVo(Const.FAIL);
        }

        return new ResVo(result);
    }

    // 선생님 정보 수정
    public ResVo putTeacher(MultipartFile teacherProfile, TeacherPatchDto dto) {
        if (dto.getIlevel() < 3) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        try {
            String path = "/user/" + dto.getIteacher();
            myFileUtils.delFolderTrigger(path);
            String savedFileNm = myFileUtils.transferTo(teacherProfile, path);
            dto.setTeacherProfile(savedFileNm);
            int affectedRows = mapper.updTeacher(dto);
            if (affectedRows > 0) {
                return new ResVo(Const.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResVo(Const.FAIL);
    }

    // 선생님 정보 삭제
    public ResVo delTeacher(TeacherDelDto dto) {
        if (dto.getIlevel() < 3) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        try {
            if (dto.getIlevel() == 3) { // swagger test 시 level 값을 3으로 입력 시 성공
                int affectedRows = mapper.isDelTeacher(dto);
                if (affectedRows > 0) {
                    return new ResVo(Const.SUCCESS);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResVo(Const.FAIL);
    }


}