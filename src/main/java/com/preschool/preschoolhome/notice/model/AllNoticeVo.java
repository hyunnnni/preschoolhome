package com.preschool.preschoolhome.notice.model;

import lombok.Data;

import java.util.List;

@Data
public class AllNoticeVo {
    private List<SelAllNoticeVo> list;
    private int noticeCnt;
}
