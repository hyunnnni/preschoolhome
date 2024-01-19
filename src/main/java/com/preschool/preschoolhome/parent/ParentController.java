package com.preschool.preschoolhome.parent;

import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.parent.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/parent")
@Tag(name = "부모님", description = "부모님 등록 삭제 외 수정, 아이 연결 ")
public class ParentController {
    private final ParentService service;

    //식별코드매칭
    @GetMapping("/check")
    @Operation(summary = "식별코드 체크", description = "<strong>식별코드 체크</strong><br><br>" +
            "식별코드로 회원가입 전 검증단계<br>" +
            "성공시 원아 정보응답<br>" +
            "실패시 에러메세지송출 <br>")
    public CodeVo postParentCheck(@RequestBody CodeDto dto) {
        return service.getMatch(dto);
    }


    //부모아이디 중복확인
    @GetMapping("/signup")
    @Operation(summary = "아이디 중복체크", description = "<strong>아이디 중복 체크</strong><br><br>" +
            "회원가입 전 아이디 중복체크<br>" +
            "성공시 1로 응답<br>" +
            "실패시 에러메세지송출 <br>")
    public ResVo postParentSignup(ParentInsDto dto) {
        return service.chekUid(dto);
    }

    //부모회원가입
    @PostMapping("/signup")
    @Operation(summary = "부모님 회원가입", description = "<strong>부모님 회원가입</strong><br><br>" +
            "이상없을 경우 회원가입 성공<br>" +
            "성공시 1로 응답<br>" +
            "실패시 에러메세지송출 <br>")
    public ResVo postSignup(@RequestBody ParentInsDto dto) {
        return service.insParent(dto);
    }

    //부모님 로그인
    @PostMapping("/signin")
    @Operation(summary = "로그인", description = "<strong>부모님 로그인</strong><br><br>" +
            "uid와 upw로 로그인<br>" +
            "성공시 아이PK, 부모 PK 응답<br>" +
            "실패시 에러메세지송출 <br>")
    public ParentKid postParentSignin(@RequestBody ParentSigninDto dto){
        return service.parentSignin(dto);
    }

    @GetMapping("/edit")
    @Operation(summary = "수정 전 정보가져오기", description = "<strong>정보 불러오기</strong><br><br>" +
            "수정전 정보가져오기<br>" +
            "성공시 원래 정보 가져오기, 부모 PK 응답<br>" +
            "실패시 에러메세지송출 <br>")
    public ParentBeforInfoVo getParentEdit(@RequestBody ParentBeforinfoDto dto){
        return service.getParentEdit(dto);
    }

    @PutMapping("/putparent")
    @Operation(summary = "부모님 정보 수정", description = "<strong>정보수정</strong><br><br>" +
            "정보 수정<br>" +
            "성공시 1 응답<br>" +
            "실패시 -1 <br>")
    public ResVo putParent(@RequestBody UpParentDto dto){
        return service.putParent(dto);
    }


    //부모 마이페이지상 식별코드로 원아등록
    @PostMapping("/kidadd")
    @Operation(summary = "마이페이지에서 원아연결", description = "<strong>원아 연결</strong><br><br>" +
            "식별코드로 부모가 원아 연결<br>" +
            "성공시 원아 정보로 응답<br>" +
            "실패시 에러메세지송출 <br>")
    public CodeVo postParentKidAdd(@RequestBody CodeDto dto) {
        return service.postKidCode(dto);
    }

    @PatchMapping
    @Operation(summary = "부모님 탈퇴", description = "<strong>부모님 탈퇴</strong><br><br>" +
            "자진탈퇴시 isdel 1로 정보 숨기기<br>" +
            "성공시 isdel 1로 응답<br>" +
            "실패시 에러메세지송출 <br>")
    public ResVo delParentSelf(@RequestBody ParentDeleteDto dto){
        return service.delParentSelf(dto);

    }

}




