package com.preschool.preschoolhome.openApi.medel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(title = "응답되는 야간진료과 데이터 리스트와 총 개수")
public class TotalNightHospitalVo {
    @Schema(title = "야간진료과 데이터")
    private List<NightHospitalVo> dataList;
    @Schema(title = "조회된 데이터의 총 개수")
    private int totalData;
}
