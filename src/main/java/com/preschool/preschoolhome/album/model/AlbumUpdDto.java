package com.preschool.preschoolhome.album.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class AlbumUpdDto {
    //    @JsonIgnore
    private int ilevel;

    @Schema(title = "선생님 PK")
    private int iteacher;
    @Schema(title = "활동 앨범 제목")
    private String albumTitle;
    @Schema(title = "활동 앨범 내용")
    private String albumContents;
    @Schema(title = "활동 앨범 PK")
    private int ialbum;
    @Schema(title = "활동 앨범에 첨부한 사진들")
    private List<String> albumPic;
}
