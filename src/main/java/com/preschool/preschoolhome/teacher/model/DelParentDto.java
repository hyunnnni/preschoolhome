package com.preschool.preschoolhome.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschoolhome.common.utils.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "관리자 입장에서 학부모 계정 삭제 시 필요한 데이터")
public class DelParentDto {
    @Schema(title = "학부모PK(여러 개 선택가능)")
    private List<Integer> iparents;
    @JsonIgnore
    private int ilevel;
    @JsonIgnore
    @Schema(title = "삭제 처리")
    private int prIsDel = Const.FAKE_IS_DEL;
}
