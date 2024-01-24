package com.preschool.preschoolhome.album.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import static com.preschool.preschoolhome.common.utils.Const.ROW_COUNT;

@Data
public class AlbumSelDto {
    @Schema(title = "page")
    private int page;
    @JsonIgnore
    @Schema(title = "페이징 처리 시작")
    private int startIdx;
    @JsonIgnore
    @Schema(title = "페이징 처리 끝")
    private int rowCount;



    public void setPage(int page) {
        this.rowCount = ROW_COUNT;
        this.startIdx = (page - 1) * rowCount;
    }
}
