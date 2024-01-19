package com.preschool.preschoolhome.album.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import static com.preschool.preschoolhome.common.utils.Const.MAIN_ROW_COUNT;
import static com.preschool.preschoolhome.common.utils.Const.MAIN_ROW_COUNT;

@Data
public class AlbumMainDto {
    @Schema(title = "page")
    private int page;
    @JsonIgnore
    @Schema(title = "페이징 처리 시작")
    private int startIdx;
    @JsonIgnore
    @Schema(title = "페이징 처리 끝")
    private int rowCount;


    public void setPage(int page) {
        this.startIdx = (page - 1) * rowCount;
        this.rowCount = MAIN_ROW_COUNT;
    }
}
