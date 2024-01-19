package com.preschool.preschoolhome.kid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "원아 발달사항")
public class KidGrowth {
    @Schema(title = "키", type = "int")
    private int height;
    @Schema(title = "체중")
    private int weight;
    @Schema(title = "측정일", type = "String")
    private String bodyDate;
    @Schema(title = "칭찬카드")
    private int growth;
    @Schema(title = "입력일", type = "String")
    private String growthDate;
    @Schema(title = "발달사항 메모", type = "String")
    private String growthMemo;
    @JsonIgnore
    private int bodyQuarterly;
    @JsonIgnore
    private int growthQuarterly;
}
