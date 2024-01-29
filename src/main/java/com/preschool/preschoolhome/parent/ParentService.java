package com.preschool.preschoolhome.parent;

import com.google.api.gax.rpc.ApiException;
import com.google.rpc.context.AttributeContext;
import com.preschool.preschoolhome.common.security.AuthenticationFacade;
import com.preschool.preschoolhome.common.security.JwtTokenProvider;
import com.preschool.preschoolhome.common.security.MyPrincipal;
import com.preschool.preschoolhome.common.security.MyUserDetails;
import com.preschool.preschoolhome.common.utils.AppProperties;
import com.preschool.preschoolhome.common.utils.Const;
import com.preschool.preschoolhome.common.utils.CookieUtils;
import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.common.exception.*;
import com.preschool.preschoolhome.parent.model.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ParentService {
    private final ParentMapper mapper;

    private final JwtTokenProvider jwtTokenProvider;
    private final AppProperties appProperties;
    private final CookieUtils cookieUtils;
    private final AuthenticationFacade authenticationFacade;
    private final PasswordEncoder passwordEncoder;

    //식별코드 매칭
    public CodeVo getMatch(String code) {
        CodeDto dto = new CodeDto();
        dto.setCode(code);
        CodeVo vo = mapper.selCode(dto);
        if (vo == null) {
            throw new RestApiException(AuthErrorCode.CHECK_CODE);
        }
        List<Integer> iparent = mapper.connectParent(vo.getIkid());
        if (iparent.size() > 2) {
            throw new RestApiException(AuthErrorCode.NOT_CONNETCT_KID);
        }
        return vo;

    }

    //아이디중복체크
    public CodeCorrect chekUid(String uid) {
        String checkUid = mapper.checkParentInfo(uid);
        CodeCorrect response = new CodeCorrect();

        if (checkUid != null) {
            throw new RestApiException(AuthErrorCode.ALREADY_EXIST_ID);
        }
        if (uid == null) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ID);
        }
        if (checkUid == null) {
            response.setIsValid(1); //회원가입 가능
            response.setMessage("사용가능한 아이디");
        }
        return response;
    }

    //회원가입, 성공시 t_parent_kid 테이블에 ikidPK, iparentPK 인서트
    public ResVo insParent(ParentInsDto dto) {

        if (dto.getIsValid() != 1) {
            throw new RestApiException(AuthErrorCode.CHECK_DUPLICATION_ID);
        }
        String checkParent = mapper.checkParentInfo(dto.getUid());
//        if(checkUid != null){
//            throw new RestApiException(AuthErrorCode.ALREADY_EXIST_ID);
//        }

        if (checkParent == null) {
            mapper.insParent(dto);
            ParentKid pk = new ParentKid();
            pk.setIkid(dto.getIkid());
            pk.setIparent(dto.getIparent());
            mapper.insParentKidTable(pk);

            return new ResVo(1);
        } else {
            // 데이터베이스 삽입이 실패한 경우
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //부모님 로그인
    public ParentKid parentSignin(HttpServletRequest req, HttpServletResponse res, ParentSigninDto dto) {
        ParentEntity entity = mapper.checkParentsId(dto);
        String upw = mapper.checkParentInfo(dto.getUid());
        if (upw == null) {
            throw new RestApiException(AuthErrorCode.NOT_EXIST_USER_ID);
        } else if (!dto.getUpw().equals(upw)) {
            throw new RestApiException(AuthErrorCode.VALID_PASSWORD);
        }
        if(entity.getPrIsDel() == 1){
            throw new RestApiException(AuthErrorCode.DELETE_ID);
        }
        ParentKid pk = new ParentKid();
        dto.setIparent(entity.getIparent());
        pk.setKidList(mapper.selKid(dto.getIparent()));

        if (dto.getUid() != null && dto.getUpw() != null && dto.getUpw().equals(entity.getUpw())) {
            pk.setIparent(entity.getIparent());
//            pk.setKidList(entity.getIkid());

        }
        MyPrincipal myPrincipal = MyPrincipal.builder()
                .iuser(entity.getIparent())
                .ilevel(entity.getIlevel())
                .build();
        String at = jwtTokenProvider.generateAccessToken(myPrincipal);
        String rt = jwtTokenProvider.generateRefreshToken(myPrincipal);

        int rtCookieMaxAge = appProperties.getJwt().getRefreshTokenCookieMaxAge();
        cookieUtils.deleteCookie(res, "rt");
        cookieUtils.setCookie(res, "rt", rt, rtCookieMaxAge);

        HttpSession session = req.getSession(true);
        session.setAttribute("loginUserPk", entity.getIparent());

        pk.setAccessToken(at);
        pk.setResult(Const.SUCCESS);
        return pk;
    }

    //원래정보 불러오기
    public ParentBeforInfoVo getParentEdit() {
        int level = authenticationFacade.getLevelPk();
        int iparent = authenticationFacade.getLoginUserPk();
        if (level != 1) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        ParentBeforInfoVo vo = mapper.selBeforeInfo(iparent);
        return vo;
    }


    //부모 마이페이지 정보수정
    public ResVo putParent(UpParentDto dto) {
        int loginUserPk = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        dto.setIparent(loginUserPk);
        dto.setIlevel(level);
        if (dto.getParentNm() == null && dto.getPhoneNb() == null && dto.getPrEmail() == null
                && dto.getUpw() == null) {
            return new ResVo(-1);

        }

        int result1 = mapper.putParent(dto);
        if (result1 == 0) {
            return new ResVo(-1);
            //throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
        return new ResVo(1);

    }

    //마이페이지 원아추가
    public CodeVo postKidCode(CodeDto dto) {
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIparent(loginUserPk);
        CodeVo vo = mapper.selCode(dto);

        if (dto.getCode() == null) {
            throw new RestApiException(AuthErrorCode.CHECK_CODE);
        }
        if(dto.getCode() == vo.getCode()){
            throw new RestApiException(AuthErrorCode.ALREADY_CONNECTION);
        }
        List<Integer> iparent = mapper.connectParent(vo.getIkid());
        if (iparent.size() > 2) {
            throw new RestApiException(AuthErrorCode.NOT_CONNETCT_KID);
        } else {
            ParentKid pk = new ParentKid();
            pk.setIkid(vo.getIkid());
            pk.setIparent(dto.getIparent());
            mapper.insParentKidTable(pk);
            return vo;
        }
    }

    //부모 정보 삭제
    public ResVo delParentSelf(ParentDeleteDto dto) {
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIparent(loginUserPk);

        int delete = mapper.delParent(dto);
        if (delete == 0) {
            throw new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION);

        } else
            return new ResVo(1);
    }

    public ParentKid getRefreshToken(HttpServletRequest req) {//at를 다시 만들어줌
        Cookie cookie = cookieUtils.getCookie(req, "rt");
        ParentKid vo = new ParentKid();
        if (cookie == null) {
            vo.setResult(Const.FAIL);
            vo.setAccessToken(null);
            return vo;
        }
        String token = cookie.getValue();
        if (!jwtTokenProvider.isValidateToken(token)) {
            vo.setResult(Const.FAIL);
            vo.setAccessToken(null);
            return vo;
        }
        MyUserDetails myUserDetails = (MyUserDetails) jwtTokenProvider.getUserDetailsFromToken(token);
        MyPrincipal myPrincipal = myUserDetails.getMyPrincipal();
        String at = jwtTokenProvider.generateAccessToken(myPrincipal);
        vo.setResult(Const.SUCCESS);
        vo.setAccessToken(at);
        return vo;
    }

    public ResVo patchUserFirebaseToken(UserFirebaseTokenPatchDto dto) {
        int affectedRows = mapper.updUserFirebaseToken(dto);
        return new ResVo(affectedRows);
    }
}














