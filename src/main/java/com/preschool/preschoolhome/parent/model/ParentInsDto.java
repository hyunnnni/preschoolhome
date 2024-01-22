package com.preschool.preschoolhome.parent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "부모 정보 입력", title = "식별코드 인증 후 회원가입")
public class ParentInsDto {
    @JsonIgnore
    private int ikid;
    @JsonIgnore
    private int iparent;
    @Schema(title = "이메일")
    private String prEmail;
    @Schema(title = "아이와의 관계")
    private int irelation;
    @Schema(title = "아이디 중복체크")
    private int isValid; // 중복체크가 되었는지 확인 (1: 문제없음, 나머지: 문제있음)
    @Schema(title = "부모이름")
    private String parentNm;
    @Schema(title = "휴대폰번호")
    private String phoneNb;
    @Schema(title = "아이디")
    private String uid;
    @Schema(title = "비밀번호")
    private String upw;
    @JsonIgnore
    private String code;

}
