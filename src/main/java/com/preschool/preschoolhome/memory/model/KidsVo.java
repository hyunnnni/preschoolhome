package com.preschool.preschoolhome.memory.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class KidsVo {
    @Schema(title = "원아 PK")
    private int ikid;
    @Schema(title = "원아이름")
    private String kidNm;
}
