package com.preschool.preschoolhome.parent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "Parent수정 전 정보")
public class ParentBeforinfoDto {
    @Schema(title = "부모PK")
    private int iparent;
    @Schema(title = "등급PK")
    private int ilevel;
}
