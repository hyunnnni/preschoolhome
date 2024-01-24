package com.preschool.preschoolhome.teacher.model;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TeacherEntity {
    private int iteacher;
    private int ilevel;
    private int iclass;
    private String teacherUid;
    private String teacherUpw;
    private String teacherNm;
    @Pattern(regexp = "^[a-z|A-Z|0-9|_|-]+@([a-z|0-9]{3,}\\.[a-z]{2,}|[a-z|0-9]{3,}\\.[a-z]{2,}\\.[a-z]{2,})$")
    private String tcEmail;
    private String teacherIntroduce;
    private String teacherProfile;
    private String accessToken;
    private String firebaseToken;
    private String createdAt;
}
