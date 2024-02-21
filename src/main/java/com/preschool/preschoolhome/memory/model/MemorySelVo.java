package com.preschool.preschoolhome.memory.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "전체 원아 조회")
public class MemorySelVo {
    private int ikid;
    private int iclass;
    private String kidNm;

}
