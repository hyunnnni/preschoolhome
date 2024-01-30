package com.preschool.preschoolhome.fullnotice.model;

import com.preschool.preschoolhome.album.model.AlbumSelVo;
import lombok.Data;

import java.util.List;

@Data
public class AllFullNoticeSelVo {
    private int noticeCnt;
    private List<SelFullNoticeVo> list;
}
