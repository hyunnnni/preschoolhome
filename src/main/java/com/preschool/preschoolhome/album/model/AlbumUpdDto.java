package com.preschool.preschoolhome.album.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class AlbumUpdDto {
    @JsonIgnore
    private int ilevel;

    @Schema(title = "선생님 PK")
    @Positive(message = "잘못된 값입니다")
    private int iteacher;

    @Schema(title = "활동 앨범 제목")
    @NotBlank(message = "제목을 입력해주세요")
    private String albumTitle;

    @Schema(title = "활동 앨범 내용")
    @NotBlank(message = "내용을 입력해주세요")
    private String albumContents;

    @Schema(title = "활동 앨범 PK")
    @Positive(message = "잘못된 값입니다")
    private int ialbum;

    @Schema(title = "활동 앨범에서 삭제 처리할 앨범 PK")
    private List<Integer> ialbumPic;

    @JsonIgnore
    @Schema(title = "활동 앨범에 첨부한 사진들")
    private List<String> albumPic;
}
