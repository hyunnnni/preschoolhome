package com.preschool.preschoolhome.notice;

import com.preschool.preschoolhome.common.utils.Const;
import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.notice.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notice")
@Tag(name = "알림장 기능 API", description = "알림장 관련 기능 설정")
public class NoticeController {
    private final NoticeService service;

    //----------------------------- 선생님 알림장 등록 --------------------------------

    @Operation(summary = "선생님 알림장 등록", description = "선생님 알림장 등록")
    @PostMapping
    public ResVo postInsNoticeTea(@RequestPart(required = false) List<MultipartFile> pics,
            @RequestPart @Valid NoticeInsDto dto){
        return service.insNotice(pics, dto);
    }

    //----------------------------- 학부모 알림장 등록 --------------------------------

    @Operation(summary = "학부모 알림장 등록", description = "학부모 알림장 등록")
    @PostMapping
    public ResVo postInsNoticePar(@RequestPart(required = false) List<MultipartFile> pics,
                               @RequestPart @Valid NoticeInsDto dto){
        return service.insNotice(pics, dto);
    }

    //-------------------------------- 알림장 수정 시 정보 출력--------------------------------

    @Operation(summary = "알림장 기존 정보 조회", description = "알림장 수정 시 기존 정보 조회")
    @GetMapping("/edit")
    public NoticeUpdSelVo getNoticeEdit(int inotice, int ikid){
        return service.noticeEdit(inotice, ikid);
    }

    //-------------------------------- 알림장 수정 --------------------------------
    @Operation(summary = "알림장 수정", description = "알림장 수정")
    @PutMapping
    public ResVo putUpdNotice(@RequestPart(required = false) List<MultipartFile> pics,
                              @RequestPart @Valid NoticeUpdDto dto){
        return service.updNotice(pics, dto);
    }

    //-------------------------------- 알림장 삭제 --------------------------------

    @Operation(summary = "알림장 삭제", description = "알림장 삭제")
    @DeleteMapping
    public ResVo deleteDelNotice(int inotice){
        return service.delNotice(inotice);
    }

    //-------------------------------- 알림장 접근 유저에 따라 다르게 전체 조회 --------------------------------
    @GetMapping
    @Valid
    @Operation(summary = "3차 유치원 알림장 전체 조회", description = """
    내가 쓴 글 or 내가 받은 글 값 추가 
    로그인 유저에 따라 연결 원아 기준으로만 조회되거나 선생님은 전체 조회/ 반 조회 가능<br>
    리스트 안 result 값이<br>
    -3 : 해당 정보로 조회 시 조회되는 정보 없음<br>
    0 : 이상 없음<br>""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public AllNoticeVo getAllNotice(@RequestParam
                                             @Positive(message="잘못된 값입니다")
                                             @Schema(title = "페이징 시 필요한 데이터")
                                             int page,
                                             @RequestParam(required = false, defaultValue = "0")
                                             @PositiveOrZero(message = "잘못된 값입니다")
                                             @Schema(title = "학부모 유저가 접근 시 본인과 연결된 원아 PK / 연결된 모든 원아 조회 시 = 0")
                                             int ikid,
                                             @RequestParam(required = false, defaultValue = "0")
                                             @Range(min = Const.ZERO, max = Const.CLASS_ROSE, message = "해당 반으로 검색되는 아이가 없습니다.")
                                             @Schema(title = "반 선택")
                                             int iclass,
                                             @RequestParam
                                             @Schema(title = "년도 선택")
                                             String year,
                                             @RequestParam(required = false)
                                             @Schema(title = "검색어")
                                             String search){
        SelAllNoticeDto dto = new SelAllNoticeDto();
        dto.setPage(page);
        dto.setIkid(ikid);
        dto.setIclass(iclass);
        dto.setSearch(search);
        dto.setYear(year);
        return service.getKidManagement(dto);

    }

//-------------------------------- 알림장 상세 조회 --------------------------------

    @GetMapping("/detail")
    @Valid
    @Operation(summary = "유치원 알림장 상세 조회", description = """
    알림장 상세 조회 로그인 유저에 따라 화면이 조금 다르다<br>
    result 값이<br>
    -3 : 해당 정보로 조회 시 조회되는 정보 없음<br>
    0 : 이상 없음<br>""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public SelDetailNoticeVo getDetailNotice(
            @RequestParam
            @Schema(title = "알림장 pk")
            @Positive(message = "잘못된 값입니다")
            int inotice){
        SelDetailNoticeDto dto = new SelDetailNoticeDto();
        dto.setInotice(inotice);
        return service.getDetailNotice(dto);
    }

//-------------------------------- 알림장 댓글 등록 --------------------------------

    @PostMapping("/comment")
    @Operation(summary = "3차 유치원 알림장 댓글 등록", description = """
    푸시 기능 추가<br>
    알림장 상세 조회 로그인 유저에 따라 화면이 조금 다르다<br>
    result 값이<br>
    -1 : 댓글 작성 실패<br>
    0 : 댓글 작성 성공<br>""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResVo postNoticeComment(@RequestBody @Valid InsNoticeCommentDto dto){
        return service.postNoticeComment(dto);
    }

    //-------------------------------- 알림장 댓글 삭제 --------------------------------
    @DeleteMapping ("/comment")
    @Valid
    @Operation(summary = "유치원 알림장 댓글 삭제", description = """
        알림장 상세 조회 로그인 유저에 따라 화면이 조금 다르다<br>
        result 값이<br>
        -4 : 댓글 작성자의 pk가 잘못 입력됨<br>
        -3 : 해당 정보로 조회 시 조회되는 정보 없음<br>
        1 : 삭제 성공<br>""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResVo delNoticeComment(
            @RequestParam
            @Positive(message="잘못된 값입니다")
            @Schema(title = "삭제하고 싶은 댓글 pk")
            int inoticeComment,
            @RequestParam(required = false, defaultValue = "0")
            @PositiveOrZero(message = "잘못된 값입니다")
            @Schema(title = "삭제를 원하는 유저의 pk(관리자일 때)")
            int iteacher,
            @RequestParam(required = false, defaultValue = "0")
            @PositiveOrZero(message = "잘못된 값입니다")
            @Schema(title = "삭제를 원하는 유저의 pk(학부모일 때)")
            int iparent
    ){
        DelNoticeCommentDto dto = new DelNoticeCommentDto();
        dto.setInoticeComment(inoticeComment);
        dto.setIteacher(iteacher);
        dto.setIparent(iparent);
        return service.delNoticeComment(dto);
    }
}
