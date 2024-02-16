package com.preschool.preschoolhome.notice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschoolhome.common.utils.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Schema(title = "알림장 조회 접근 유저에 따라 다르게 조회에 필요한 데이터")
public class SelAllNoticeDto {
    @Schema(title = "페이징 시 필요한 데이터")
    private int page;

    @Schema(title = "학부모 유저가 접근 시 본인과 연결된 원아 PK / 연결된 모든 원아 조회 시 = 0")
    private int ikid;

    @Schema(title = "선생님 유저가 접근 시 원하는 원아들 반 PK /전체 조회 시 또는 맨 처음 조회 시 본인 반 먼저 조회 = 0")
    private int iclass;

    @Schema(title = "해당 원아의 알림장 업로드 년도/ 전체 조회 시 = null")
    private String year;

    @Schema(title = "내가 받은 글 = 0(기본)/ 내가 쓴 글 = 1")
    private int fromTo;

    @Schema(title = "이 페이지에 접근한 유저(선생님 OR 학부모)의 PK")
    private int loginedIuser;

    @Schema(title = "검색기능")
    private String search;

    @JsonIgnore
    private int ilevel;
    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int rowCount;

    public void setPage(int page) {
        this.rowCount = Const.PAGE_ROWCOUNT;
        this.startIdx = (page-1)*rowCount;
    }
}
