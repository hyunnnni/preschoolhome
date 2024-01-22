package com.preschool.preschoolhome.parent.model;

import lombok.Data;

@Data
public class ParentEntity {
    private int iparent;
    private int irelation;
    private int ilevel;
    private String uid;
    private String upw;
    private String parentNm;
    private String phoneNb;
    private String prEmail;
    private String firebaseToken;
    private String createdAt;
    private int ikid;
}
