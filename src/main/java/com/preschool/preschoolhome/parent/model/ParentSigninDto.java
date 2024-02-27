package com.preschool.preschoolhome.parent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "로그인")
public class ParentSigninDto {
    @JsonIgnore
    private int ikid;
    @JsonIgnore
    private int iparent;
    @Schema(title = "아이디", example = "user1")
    private String uid;
    @Schema(title = "비밀번호", example = "1234")
    private String upw;



}
