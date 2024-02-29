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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teacherNm;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String parentNm;
    private String memoryComment;
    private String createdAt;
}
