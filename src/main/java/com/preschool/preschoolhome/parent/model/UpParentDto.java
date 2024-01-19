package com.preschool.preschoolhome.parent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "부모 정보수정")
public class UpParentDto {
    private int iparent;
    private int ilevel;
    private String parentNm;
    private String phoneNb;
    private String email;
    private String upw;

}
