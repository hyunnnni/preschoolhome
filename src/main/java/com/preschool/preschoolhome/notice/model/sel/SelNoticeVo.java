package com.preschool.preschoolhome.notice.model.sel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "유치원 소식 상세 조회")
public class SelNoticeVo {
    private String fullTitle;
    private String fullContents;
    private String writer;
    private String createdAt;
    private List<String> pics;
}
