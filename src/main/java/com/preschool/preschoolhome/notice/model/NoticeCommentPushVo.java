package com.preschool.preschoolhome.notice.model;

import lombok.Data;

@Data
public class NoticeCommentPushVo {
    private int writerIuser;
    private String noticeComment;
    private String createdAt;
}
