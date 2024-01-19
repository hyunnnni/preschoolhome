package com.preschool.preschoolhome.kid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "원아 마이페이지 수정 시 조회")
public class KidDetailEditVo {
    @JsonIgnore
    private int result;
    @Schema(title = "이름", type = "String")
    private String kidNm;
    @Schema(title = "반")
    private int iclass;
    @Schema(title = "성별")
    private int gender;
    @Schema(title = "사진", type = "String")
    private String profile;
    @Schema(title = "출생일", type = "String")
    private String birth;
    @Schema(title = "주소", type = "String")
    private String address;
    @Schema(title = "메모", type = "String")
    private String memo;
    @Schema(title = "비상 연락자", type = "String")
    private String emerNm;
    @Schema(title = "비상 연락망", type = "String")
    private String emerNb;

}
