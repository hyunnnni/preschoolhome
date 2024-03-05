package com.preschool.preschoolhome.teacher.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(title = "선생님 로그인 시 응답 데이터")
public class TeacherEntity {
    private int iteacher;
    private int ilevel;
    private int iclass;
    private String teacherNm;
    private String teacherUid;
    private String teacherUpw;
    private String tcEmail;
    private String teacherIntroduce;
    private String teacherProfile;
    private String createdAt;
    private String firebaseToken;
    private int tcIsDel;
    private String role;
    private String accessToken;
}
