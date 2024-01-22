package com.preschool.preschoolhome.album.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AlbumDelDto {
    @Schema(title = "활동 앨범 PK")
    private int ialbum;
    @JsonIgnore
    private int ilevel;
}
