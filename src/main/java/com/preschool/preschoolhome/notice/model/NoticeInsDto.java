package com.preschool.preschoolhome.notice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "알림장 등록")
public class NoticeInsDto {
    @JsonIgnore
    private int inotice;
    @Schema(title = "선생님 PK")
    @Positive(message = "잘못된 값입니다")
    private int iteacher;
    @Schema(title = "원아 PK")
    @Positive(message = "잘못된 값입니다")
    private int ikid;
    @Schema(title = "알림장 제목")
    @NotBlank(message = "값을 입력해주세요")
    private String noticeTitle;
    @Schema(title = "알림장 내용")
    @NotBlank(message = "값을 입력해주세요")
    private String noticeContents;
    @JsonIgnore
    @Schema(title = "알림장 사진")
    List<String> noticePics;
}
