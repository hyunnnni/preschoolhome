package com.preschool.preschoolhome.notice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class NoticePushVo {
    private int writerIuser;
    private String noticeTitle;
    private String createdAt;
}
