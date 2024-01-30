package com.preschool.preschoolhome.teacher.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(title = "선생님이 부모 정보수정")
public class UpdTeacherParentDto {
    @Schema(title = "부모PK")
    private int iparent;
    @Schema(title = "부모이름")
    private String parentNm;
    @Schema(title = "휴대폰번호")
    private String phoneNb;
    @Schema(title = "이메일")
    @Pattern(regexp = "^[a-z|A-Z|0-9|_|-]+@([a-z|0-9]{3,}\\.[a-z]{2,}|[a-z|0-9]{3,}\\.[a-z]{2,}\\.[a-z]{2,})$")
    private String prEmail;
    @Schema(title = "비밀번호")
    private String upw;

}
