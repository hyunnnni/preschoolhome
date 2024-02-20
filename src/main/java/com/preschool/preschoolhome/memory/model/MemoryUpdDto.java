package com.preschool.preschoolhome.memory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class MemoryUpdDto {
    @Schema(title = "삭제된 사진이 있다면 그 사진의 pk들")
    private List<Integer> delPics;
    @JsonIgnore
    @Schema(title = "삭제할 글PK")
    private int imemory;
    @JsonIgnore
    private String title;
    @Schema(title = "원아 PK")
    private List<Integer> ikids;
}
