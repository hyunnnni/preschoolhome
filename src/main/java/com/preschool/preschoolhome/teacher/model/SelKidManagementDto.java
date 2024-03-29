package com.preschool.preschoolhome.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschoolhome.common.utils.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "관리자 입장에서 원아관리 페이지 조회 시 필요한 데이터 반/재원상태로도 나눌 수 있음")
public class SelKidManagementDto {
    @Schema(title = "페이징 시 필요한 데이터")
    private int page;
    @Schema(title = "조회 시 선택하는 반 OR 재원상태 데이터")
    private int kidCheck;
    @JsonIgnore
    private int ilevel;
    @Schema(title = "검색기능")
    private String search;
    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int rowCount;

    public void setPage(int page) {
        this.rowCount = Const.PAGE_ROWCOUNT;
        this.startIdx = (page-1)*rowCount;
    }
}
