package com.preschool.preschoolhome.memory.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "전체 원아 조회")
public class MemorySelDto {
    private int ikid;
    private int iclass;
    private String kidNm;

}
