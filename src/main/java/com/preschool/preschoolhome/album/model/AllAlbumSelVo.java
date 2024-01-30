package com.preschool.preschoolhome.album.model;

import lombok.Data;

import java.util.List;

@Data
public class AllAlbumSelVo {
    private int albumCnt;
    private List<AlbumSelVo> list;
}
