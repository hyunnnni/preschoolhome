package com.preschool.preschoolhome.parent.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "t_parent_kid table에 아이&부모PK Insert")
public class ParentKid {
    @JsonIgnore
    private int ikid;
    @Schema(title = "부모PK")
    private int iparent;

    @Schema(title = "아이 정보")
    private List<SelKid> kidList;

    @Schema(title = "이름")
    private String parentNm;
    @Schema(title = "이메일")
    private String prEmail;
    @Schema(title = "번호")
    private String phoneNb;

    private int result;
    private String prFirebaseToken;
    private String accessToken;
    private String role;
}
