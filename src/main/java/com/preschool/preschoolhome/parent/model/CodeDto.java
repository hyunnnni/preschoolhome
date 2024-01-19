package com.preschool.preschoolhome.parent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "코드 입력")
public class CodeDto {
    private String code;
    private int iparent;

}
