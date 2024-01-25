package com.preschool.preschoolhome.main.model;

import lombok.Data;

import java.util.List;

@Data
public class MainVo {
    private List<AlbumMainVo> albumMainVoList;
    private List<SelFullNoticeVo> fullNoticeVoList;
}
