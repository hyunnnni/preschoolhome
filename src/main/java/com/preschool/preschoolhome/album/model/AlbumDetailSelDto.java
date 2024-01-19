package com.preschool.preschoolhome.album.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class AlbumDetailSelDto {
//    @JsonIgnore
    private int ilevel;
    @Schema(title = "활동 앨범 PK")
    private int ialbum;
    @JsonIgnore
    private List<MultipartFile> albumPic;
}
