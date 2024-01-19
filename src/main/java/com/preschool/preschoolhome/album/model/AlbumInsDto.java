package com.preschool.preschoolhome.album.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class AlbumInsDto {
    @Schema(title = "선생님 PK")
    private int iteacher;
    @Schema(title = "활동 앨범 제목")
    private String albumTitle;
    @Schema(title = "활동 앨범 내용")
    private String albumContent;
    @JsonIgnore
    private List<MultipartFile> albumPic;
    //    @JsonIgnore
    private int ilevel;
    @JsonIgnore
    private int ialbum;
}
