package com.preschool.preschoolhome.memory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MemoryCommentVo {
    private int imemoryComment;
    private int writerIuser;
    private String writerNm;
    private String memoryComment;
    private String createdAt;
}
