package com.preschool.preschoolhome.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschoolhome.common.utils.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TeacherDelDto {
    @Schema(title = "삭제할 선생님 PK")
    private int iteacher;
    @Schema(title = "선생님 계정 삭제 여부")
    @JsonIgnore
    private int tcIsDel = Const.FAKE_IS_DEL;
}
