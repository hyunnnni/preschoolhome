package com.preschool.preschoolhome.parent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "Parent수정 전 정보")
public class ParentBeforinfoDto {
    private int iparent;
    private int ilevel;
}
