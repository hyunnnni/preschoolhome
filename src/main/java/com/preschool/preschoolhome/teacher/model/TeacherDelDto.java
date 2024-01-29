package com.preschool.preschoolhome.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TeacherDelDto {
    @JsonIgnore
    private int ilevel;
    @Schema(title = "삭제할 선생님 PK")
    private int iteacher;
}
