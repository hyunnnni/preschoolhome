package com.preschool.preschoolhome.notice.model.sel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "유치원 소식 전체 조회")
public class SelFullNoticeVo {
    private String fullTitle;
    private String writer;
    private int fullNoticeFix;
    private String createdAt;


}
