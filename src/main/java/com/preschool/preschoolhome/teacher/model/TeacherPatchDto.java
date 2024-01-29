package com.preschool.preschoolhome.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TeacherPatchDto {
    @Schema(title = "선생님 PK")
    @Positive(message = "잘못된 값입니다")
    private int iteacher;

    @Schema(title = "선생님 이름")
    @NotBlank(message = "이름을 입력해주세요")
    private String teacherNm;

    @JsonIgnore
    private int ilevel;

    @Schema(title = "반 PK")
    @Positive(message = "잘못된 값입니다")
    private int iclass;

    @Schema(title = "선생님 ID")
    @NotBlank(message = "아이디를 입력해주세요")
    private String teacherUid;

    @Schema(title = "선생님 PW")
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String teacherUpw;

    @Schema(title = "선생님 이메일")
    @NotBlank(message = "이메일을 입력해주세요")
    private String tcEmail;

    @Schema(title = "선생님 프로필 사진")
    @JsonIgnore
    private String teacherProfile;

    @Schema(title = "선생님 소개글")
    @NotBlank(message = "소개글을 입력해주세요")
    private String teacherIntroduce;
}
