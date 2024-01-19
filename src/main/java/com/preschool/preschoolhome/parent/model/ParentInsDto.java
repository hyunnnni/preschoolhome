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
    private String email;
    private int irelation;
    private int isValid; // 중복체크가 되었는지 확인 (1: 문제없음, 나머지: 문제있음)
    private String parentNm;
    private String phoneNb;
    private String uid;
    private String upw;
    @JsonIgnore
    private String code;

}
