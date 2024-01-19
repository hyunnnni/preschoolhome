package com.preschool.preschoolhome.parent.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "t_parent_kid table에 아이&부모PK Insert")
public class ParentKid {
    private int iparent;
    private int ikid;
}
