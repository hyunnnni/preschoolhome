package com.preschool.preschoolhome.main;


import com.preschool.preschoolhome.main.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {

    // 메인 활동 앨범
    List<AlbumMainVo> selMainAlbum (MainDto dto);
    // 게시판 전체 조회 및 상세조회
    List<SelFullNoticeVo> getFullNoticeFix();
}
