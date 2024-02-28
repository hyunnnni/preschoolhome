package com.preschool.preschoolhome.notice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "알림장 업로드 시 사용 될 응답 데이터")
public class NoticePushVo {
    @Schema(title = "알림장 업로드한 유저 pk")
    private int writerIuser;
    @Schema(title = "알림장 업로드한 유저 이름")
    private String writerNm;
    @Schema(title = "알림장 이름")
    private String noticeTitle;
    @Schema(title = "알림장 작성 시 푸시알림이 가는 날짜")
    private String createdAt;
    @Schema(title = "알림장 PK")
    private int inotice;
    @Schema(title = "알림장 대상 원아PK")
    private int ikid;
    @Schema(title = "알림장 대상 원아 이름")
    private String kidNm;
}
