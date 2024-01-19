package com.preschool.preschoolhome.album.model;

import lombok.Data;

@Data
public class AlbumAllCommentVo {
    private int ialbum;
    private String albumComment;
    private String createdAt;
    private int iteacher;
    private int iparent;
}
