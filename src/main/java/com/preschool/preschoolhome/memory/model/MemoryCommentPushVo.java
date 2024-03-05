package com.preschool.preschoolhome.memory.model;

import lombok.Data;

@Data
public class MemoryCommentPushVo {
    private int imemory;
    private int ikid;
    private String kidNm;
    private int writerIuser;
    private String memoryComment;
    private int cmtWriterIuser;
    private String cmtWriterNm;
    private String cmtCreatedAt;
}
