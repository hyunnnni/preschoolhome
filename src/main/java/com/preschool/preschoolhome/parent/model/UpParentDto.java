package com.preschool.preschoolhome.parent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "부모 정보수정")
public class UpParentDto {
    @Schema(title = "부모PK")
    private int iparent;
    @Schema(title = "등급PK")
    private int ilevel;
    @Schema(title = "부모이름")
    private String parentNm;
    @Schema(title = "휴대폰번호")
    private String phoneNb;
    @Schema(title = "이메일")
    private String email;
    @Schema(title = "비밀번호")
    private String upw;

}
