package com.preschool.preschoolhome.parent;

import com.preschool.preschoolhome.parent.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParentMapper {
    CodeVo selCode(CodeDto dto); //식별코드셀렉

    String checkParentInfo(String uid);//아이디중복체크

    int insParent(ParentInsDto dto); //부모회원가입

    List<Integer> connectParent(int iparent);

    ParentEntity selParent(ParentSigninDto dto); //부모님 로그인 셀렉

    ParentEntity checkParentsId(ParentSigninDto dto); //비밀번호 가져오기

    int insParentKidTable(ParentKid dto); //마이페이지 아이 추가

    ParentBeforInfoVo selBeforeInfo(int ilevel); //원래정보 불러오기

    int putParent(UpParentDto dto);//마이페이지 정보수정

    int delParent(ParentDeleteDto dto);

    int updUserFirebaseToken(UserFirebaseTokenPatchDto dto);




}
