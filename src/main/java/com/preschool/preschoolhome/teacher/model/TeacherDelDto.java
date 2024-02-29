package com.preschool.preschoolhome.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschoolhome.common.utils.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TeacherDelDto {
    @Schema(title = "선생님PK(여러 개 선택 가능)")
    private List<Integer> iteachers;
    @Schema(title = "삭제 처리 or 재직 되돌리기")
    private int tcIsDel;
    @JsonIgnore
    private int ilevel;
}

