package com.preschool.preschoolhome.teacher.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TeacherDelDto {
    @Schema(title = "선생님을 삭제할 원장님 직급 PK")
    private int ilevel;
    @Schema(title = "삭제할 선생님 PK")
    private int iteacher;
    @Schema(title = "선생님 계정 삭제 여부")
    private int tcDel;
}
