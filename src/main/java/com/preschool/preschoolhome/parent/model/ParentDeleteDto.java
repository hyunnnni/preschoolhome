package com.preschool.preschoolhome.parent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "회원 탈퇴")
public class ParentDeleteDto {
    @Schema(title = "부모PK")
    private int iparent;
}