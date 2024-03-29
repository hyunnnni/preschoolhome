package com.preschool.preschoolhome.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschoolhome.common.utils.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Data
public class SelAllTeacherDto {
    @Schema(title = "반PK")
    private int iclass;
    @Schema(title = "페이징 시 필요한 데이터")
    private int page;
    @Schema(title ="퇴사한 선생님 조회")
    private int tcIsDel;
    @Schema(title = "검색기능")
    private String search;
    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int rowCount;

    public void setPage(int page) {
        this.rowCount = Const.ROW_COUNT;
        this.startIdx = (page - 1) * rowCount;
        this.page = page;

    }
}
