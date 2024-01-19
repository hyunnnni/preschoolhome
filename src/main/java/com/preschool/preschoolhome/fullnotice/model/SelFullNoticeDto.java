package com.preschool.preschoolhome.fullnotice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschoolhome.common.utils.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "유치원 소식 전체 조회")
public class SelFullNoticeDto {
    @JsonIgnore//시큐리티 쓰면 지울 거
    private int ilevel;
    @Schema(title="페이지")
    private int page;
    @JsonIgnore
    private int starIdx;
    @JsonIgnore
    private int row= Const.NOTICE_COUNT_PER_PAGE;




    public void setPage(int page){

        this.starIdx=(page-1)*row;
    }
}

