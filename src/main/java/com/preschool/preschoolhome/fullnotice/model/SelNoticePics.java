package com.preschool.preschoolhome.fullnotice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class SelNoticePics {
    @Schema(title = "유치원 소식 사진")
    private String pic;
    @Schema(title = "유치원 소식 사진PK")
    private int ifullPic;
}
