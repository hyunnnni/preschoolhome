package com.preschool.preschoolhome.memory.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MemoryPicsInsDto {
    @Schema(title = "추억 앨범 글 PK")
    private int imemory;
    @Schema(title = "추억 앨범 사진")
    private List<String> memoryPics = new ArrayList<>();
}
