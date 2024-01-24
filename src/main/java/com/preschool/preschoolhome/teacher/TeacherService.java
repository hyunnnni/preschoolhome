package com.preschool.preschoolhome.teacher;

import com.preschool.preschoolhome.common.exception.AuthErrorCode;
import com.preschool.preschoolhome.common.exception.CommonErrorCode;
import com.preschool.preschoolhome.common.exception.PreschoolErrorCode;
import com.preschool.preschoolhome.common.exception.RestApiException;
import com.preschool.preschoolhome.common.security.AuthenticationFacade;
import com.preschool.preschoolhome.common.security.JwtTokenProvider;
import com.preschool.preschoolhome.common.security.MyPrincipal;
import com.preschool.preschoolhome.common.utils.*;
import com.preschool.preschoolhome.parent.model.ParentEntity;
import com.preschool.preschoolhome.parent.model.ParentKid;
import com.preschool.preschoolhome.parent.model.ParentSigninDto;
import com.preschool.preschoolhome.teacher.model.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
    private final AuthenticationFacade authenticationFacade;
    private final JwtTokenProvider jwtTokenProvider;
    private final AppProperties appProperties;
    private final CookieUtils cookieUtils;

    //-------------------------------- 원아 관리 페이지 조회 --------------------------------
    public List<SelKidManagementVo> getKidManagement(SelKidManagementDto dto){
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

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
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
            return new ResVo(Const.NO_PERMISSION);
        }

        int result = mapper.updKidStateOrClass(dto);
        DelStateParentIsProc pDto = new DelStateParentIsProc();
        int delResult = Const.RESULT;  ///이거 확인 1@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

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
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);
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
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);
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
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);
        if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
            return new ResVo(Const.NO_PERMISSION);
        }

        int result = mapper.delDisconnent(dto);

        if(result == 0){
            return new ResVo(Const.FAIL);
        }

        return new ResVo(result);
    }

    // 선생님 정보 수정 시 불러오기
    public TeacherEditVo selTeacherEdit (int iteacher, int ilevel) {
        if (ilevel < 3) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        try{
            return mapper.selTeacherEdit(iteacher);
        } catch (Exception e) {
            // 예외 발생 시 에러 메시지 띄우기
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    // 선생님 정보 수정
    public ResVo putTeacher(MultipartFile teacherProfile, TeacherPatchDto dto) {
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);
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
            // 예외 발생 시 에러 메시지 띄우기
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
        return new ResVo(Const.FAIL);
    }

    // 선생님 정보 삭제
    public ResVo delTeacher(TeacherDelDto dto) {
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);
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
            // 예외 발생 시 에러 메시지 띄우기
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
        return new ResVo(Const.FAIL);
    }

    public TeacherEntity teacherSignin(HttpServletRequest req, HttpServletResponse res, TeacherSigninDto dto) {
        TeacherEntity entity = mapper.selTeacher(dto);

        String upw = mapper.checkTeacherInfo(dto.getTeacherUid());

        if (upw == null) {
            throw new RestApiException(AuthErrorCode.NOT_EXIST_USER_ID);

        } else if (!dto.getTeacherUpw().equals(upw)) {
            throw new RestApiException(AuthErrorCode.VALID_PASSWORD);
        }

        MyPrincipal myPrincipal = MyPrincipal.builder()
                .iuser(entity.getIteacher())
                .ilevel(entity.getIlevel())
                .build();
        String at = jwtTokenProvider.generateAccessToken(myPrincipal);
        String rt = jwtTokenProvider.generateRefreshToken(myPrincipal);

        int rtCookieMaxAge = appProperties.getJwt().getRefreshTokenCookieMaxAge();
        cookieUtils.deleteCookie(res, "rt");
        cookieUtils.setCookie(res, "rt", rt, rtCookieMaxAge);

        HttpSession session = req.getSession(true);
        session.setAttribute("loginUserPk", entity.getIteacher());

        entity.setAccessToken(at);

        return entity;
    }
}