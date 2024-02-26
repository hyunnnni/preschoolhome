package com.preschool.preschoolhome.openApi.medel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(title = "응답되는 소아기관 데이터 리스트와 총 개수")
public class TotalDataVo {
    @Schema(title = "소아기관 데이터")
    private List<DataVo> dataList;
    @Schema(title = "조회된 데이터의 총 개수")
    private int totalData;
}
