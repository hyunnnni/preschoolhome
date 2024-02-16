package com.preschool.preschoolhome.teacher.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(title = "선생님 정보 & 명 수 불러오기")
public class SelTeacherInfoVo {
    @Schema(title = "선생님 명 수")
    private int teacherCnt;
    @Schema(title = "선생님 정보 리스트")
    private List<SelAllTeacherVo> list = new ArrayList();

}
