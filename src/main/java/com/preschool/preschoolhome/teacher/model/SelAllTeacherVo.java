package com.preschool.preschoolhome.teacher.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "3차 선생님 전체 or 선택조회")
public class SelAllTeacherVo {
    @Schema(title = "선생님 PK")
    private int iteacher;
    @Schema(title = "반 PK")
    private int iclass;
    @Schema(title = "선생님 이름")
    private String teacherNm;
    @Schema(title = "선생님 ID")
    private String teacherUid;
    @Schema(title = "선생님 재직상태")
    private int tcIsDel;
    @Schema(title = "선생님 email")
    private String tcEmail;
    @Schema(title = "선생님 사진")
    private String teacherProfile;



}
