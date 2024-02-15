package com.preschool.preschoolhome.notice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NoticeInsProcDto {
    @Schema(title = "유치원소식 PK")
    private int inotice;
    @Schema(title = "작성하는 유저의 PK")
    private int writerIuser;
    @Schema(title = "작성하는 유저의 등급 PK")
    private int writerIlevel;
    @Schema(title = "원아 PK")
    private int ikid;
    @Schema(title = "알림장 제목")
    private String noticeTitle;
    @Schema(title = "알림장 내용")
    private String noticeContents;
    @Schema(title = "중요 알림장")
    private int noticeCheck;
}
