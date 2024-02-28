package com.preschool.preschoolhome.memory.model;

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
    private String WriterNm;
    private String memoryComment;
    private String createdAt;
}
