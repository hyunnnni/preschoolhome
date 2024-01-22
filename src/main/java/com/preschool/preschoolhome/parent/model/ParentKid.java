package com.preschool.preschoolhome.parent.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "t_parent_kid table에 아이&부모PK Insert")
public class ParentKid {
    @Schema(title = "부모PK")
    private int iparent;
    @Schema(title = "아이PK")
    private int ikid;

    private int result;
    private String firebaseToken;
    private String accessToken;
}
