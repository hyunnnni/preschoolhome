package com.preschool.preschoolhome.memory.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class KidsVo {
    @Schema(title = "원아 PK")
    private int ikid;
    @Schema(title = "원아이름")
    private String kidNm;
}
