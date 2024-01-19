package com.preschool.preschoolhome.notice.model.sel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(title = "유치원 소식 사진불러오기")
public class SelNoticePicVo {
    @JsonIgnore
    private int ilevel;
    private List<String> pics = new ArrayList<>();
    private int iFullNotice;
}
