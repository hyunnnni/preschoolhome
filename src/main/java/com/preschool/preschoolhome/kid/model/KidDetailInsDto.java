package com.preschool.preschoolhome.kid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "원아 발달사항 등록")
public class KidDetailInsDto {
    @Schema(title = "원아 pk")
    private int ikid;
    @Schema(title = "등급 pk")
    private int irank;
    @Schema(title = "키")
    private int height;
    @Schema(title = "체중")
    private int weight;
    @Schema(title = "칭찬카드")
    private int growth;
    @Schema(title = "입력일", type = "String")
    private String growthDate;
    @Schema(title = "발달사항 메모")
    private String growthMemo;
    @Schema(title = "측정일")
    private String bodyDate;
    @JsonIgnore
    private int bodyQuarterly;
    @JsonIgnore
    private int growthQuarterly;
}
