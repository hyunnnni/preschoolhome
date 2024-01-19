package com.preschool.preschoolhome.kid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "원아 정보 수정")
public class KidUpdDto {
    @Schema(title = "원아 pk")
    private int ikid;
    @Schema(title = "이름")
    private String kidNm;
    @Schema(title = "반")
    private int iclass;
    @Schema(title = "성별")
    private int gender;
    @JsonIgnore
    private String profile;
    @Schema(title = "출생일")
    private String birth;
    @Schema(title = "주소")
    private String address;
    @Schema(title = "메모")
    private String memo;
    @Schema(title = "등급 pk")
    private int ilevel;
    @Schema(title = "비상 연락자")
    private String emerNm;
    @Schema(title = "비상 연락망")
    private String emerNb;
}
