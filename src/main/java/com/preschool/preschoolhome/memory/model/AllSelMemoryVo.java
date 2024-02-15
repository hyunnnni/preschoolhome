package com.preschool.preschoolhome.memory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "추억앨범 조회")
public class AllSelMemoryVo {
    @Schema(title = "추억앨범 PK")
    private int imemory;
    @Schema(title = "추억앨범 제목")
    private String memoryTitle;
    @Schema(title = "추억앨범 내용")
    private String memoryContents;
    @Schema(title = "작성자")
    private String teacherNm;
    @Schema(title = "추억앨범 사진")
    private List<String> memoryPic;
    @Schema(title = "댓글여부")
    private List<String> memoryComment;
    @Schema(title = "추억앨범 총 개수")
    private int imemoryCnt;
    @Schema(title = "작성시간")
    private String createdAt;


}
