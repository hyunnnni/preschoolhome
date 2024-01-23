package com.preschool.preschoolhome.album.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AlbumCommentInsDto {
    @JsonIgnore
    private int ialbumComment;
    @Schema(title = "활동 앨범 PK")
    private int ialbum;
    @Schema(title = "활동 앨범 댓글")
    private String albumComment;
    @Schema(title = "선생님 PK")
    private int iteacher;
    @Schema(title = "부모님 PK")
    private int iparent;
    @JsonIgnore
    private int ilevel;
}
