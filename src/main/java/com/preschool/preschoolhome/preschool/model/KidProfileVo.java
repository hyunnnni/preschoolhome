package com.preschool.preschoolhome.preschool.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class KidProfileVo {
    @Schema(title = "반 PK")
    private int iclass;
    @Schema(title = "아이 이름")
    private String kidNm;
    @Schema(title = "아이 사진")
    private String profile;
}
