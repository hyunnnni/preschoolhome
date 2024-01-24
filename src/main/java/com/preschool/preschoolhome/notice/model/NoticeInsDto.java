package com.preschool.preschoolhome.notice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "알림장 등록")
public class NoticeInsDto {
    @JsonIgnore
    private int inotice;
    @Schema(title = "선생님 PK")
    private int iteacher;
    @Schema(title = "원아 PK")
    private int ikid;
    @Schema(title = "알림장 제목")
    private String noticeTitle;
    @Schema(title = "알림장 내용")
    private String noticeContents;
    @Schema(title = "알림장 사진")
    List<String> noticePics;
}
