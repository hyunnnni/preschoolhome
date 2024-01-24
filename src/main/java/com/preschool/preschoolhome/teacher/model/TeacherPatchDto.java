package com.preschool.preschoolhome.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TeacherPatchDto {
    @Schema(title = "선생님 PK")
    private int iteacher;
    @Schema(title = "선생님 이름")
    private String teacherNm;
    @Schema(title = "반 PK")
    private int iclass;
    @Schema(title = "선생님 ID")
    private String teacherUid;
    @Schema(title = "선생님 PW")
    private String teacherUpw;
    @Schema(title = "선생님 이메일")
    private String tcEmail;
    @JsonIgnore
    @Schema(title = "선생님 프로필 사진")
    private String teacherProfile;
    @Schema(title = "선생님 소개글")
    private String teacherIntroduce;
}
