package com.preschool.preschoolhome.fullnotice.model;

import com.preschool.preschoolhome.album.model.AlbumSelVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "소식 조회")
public class AllFullNoticeSelVo {
    @Schema(title = "소식 개수")
    private int noticeCnt;
    @Schema(title = "소식 리스트")
    private List<SelFullNoticeVo> list;
}
