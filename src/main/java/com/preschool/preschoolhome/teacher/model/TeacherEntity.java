package com.preschool.preschoolhome.teacher.model;

import lombok.Data;

@Data
public class TeacherEntity {
    private int iteacher;
    private int ilevel;
    private int iclass;
    private String teacherUid;
    private String teacherUpw;
    private String teacherNm;
    private String tcEmail;
    private String teacherIntroduce;
    private String teacherProfile;
    private String accessToken;
    private String firebaseToken;
    private String createdAt;
}
