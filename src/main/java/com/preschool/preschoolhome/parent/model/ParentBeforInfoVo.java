package com.preschool.preschoolhome.parent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "Parent수정 전 정보 Select")
public class ParentBeforInfoVo {
    @Schema(title = "부모이름")
    private String parentNm;
    @Schema(title = "휴대폰번호")
    private String phoneNb;
    @Schema(title = "아이디")
    private String uid;
    @Schema(title = "이메일")
    private String email;

}
