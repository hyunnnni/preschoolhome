package com.preschool.preschoolhome.notice.model;

import lombok.Data;

@Data
public class NoticeCommentPushVo {
    private int inotice;
    private int ikid;
    private String kidNm;
    private int writerIuser;
    private String noticeComment;
    private int cmtWriterIuser;
    private String cmtWriterNm;
    private String cmtCreatedAt;




}
