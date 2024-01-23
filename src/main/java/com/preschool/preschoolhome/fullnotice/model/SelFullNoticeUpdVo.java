package com.preschool.preschoolhome.fullnotice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "유치원 소식 수정 시 불러오기에 필요한 응답 데이터")
public class SelFullNoticeUpdVo {
    @Schema(title = "유치원 소식 제목")
    private String fullTitle;
    @Schema(title = "유치원 소식 내용")
    private String fullContents;
    @Schema(title = "유치원 소식 작성자pk")
    private String writer;
    @Schema(title = "유치원 소식 작성자 이름")
    private String writerName;
    @Schema(title = "응답값 유치원 소식 pk")
    private String createdAt;
    @Schema(title = "유치원 소식 상단 공지 체크 : 1, 체크 해제 : 0")
    private int fullNoticeFix;
    @Schema(title = "유치원 소식 사진")
    private List<String> fullPic;
    @Schema(title = "실패 응답값")
    private int result;
}
