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
    private String uid;
    private String upw;



}
