package com.preschool.preschoolhome.parent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "Parent수정 전 정보 Select")
public class ParentBeforInfoVo {
    private String parentNm;
    private String phoneNb;
    private String uid;
    private String email;

}
