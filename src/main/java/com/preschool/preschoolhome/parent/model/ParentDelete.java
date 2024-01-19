package com.preschool.preschoolhome.parent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "Parent isDel 처리")
public class ParentDelete {
    private int iparent;
    private int isdel;
}
