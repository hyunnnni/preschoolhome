package com.preschool.preschoolhome.memory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class MemoryUpdDto {
    @Schema(title = "삭제된 사진이 있다면 그 사진의 pk들")
    private List<Integer> delPics;
    @Schema(title = "삭제할 글PK")
    private int imemory;
    @Schema(title = "글 제목")
    private String memoryTitle;
    @Schema(title = "글 내용")
    private String memoryContents;
    @Schema(title = "원아 PK")
    private List<Integer> ikids;
}
