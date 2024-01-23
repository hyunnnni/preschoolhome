package com.preschool.preschoolhome.notice;

import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.notice.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Operation(summary = "알림장 등록", description = "알림장 등록")
    @PostMapping
    public ResVo postInsNotice(@RequestPart List<MultipartFile> pics,
            @RequestPart NoticeInsDto dto){
        return service.insNotice(pics, dto);
    }

    @Operation(summary = "알림장 기존 정보 조회", description = "알림장 수정 시 기존 정보 조회")
    @GetMapping("edit")
    public NoticeUpdSelVo getNoticeEdit(int inotice, int ikid, @Min(value = 1,message = "권한이 없습니다")int ilevel){
        return service.noticeEdit(inotice, ikid);
    }

    @Operation(summary = "알림장 수정", description = "알림장 수정")
    @PutMapping
    public ResVo putUpdNotice(@RequestPart List<MultipartFile> pics,
                              @RequestPart NoticeUpdDto dto){
        return service.updNotice(pics, dto);
    }

    @Operation(summary = "알림장 삭제", description = "알림장 삭제")
    @DeleteMapping
    public ResVo deleteDelNotice(int iteacher,int inotice){
        return service.delNotice(iteacher,inotice);
    }

    //-------------------------------- 알림장 접근 유저에 따라 다르게 전체 조회 --------------------------------
    @GetMapping
    @Operation(summary = "유치원 알림장 전체 조회", description = """
    로그인 유저에 따라 연결 원아 기준으로만 조회되거나 선생님은 전체 조회/ 반 조회 가능<br>
    리스트 안 result 값이<br>
    -3 : 해당 정보로 조회 시 조회되는 정보 없음<br>
    0 : 이상 없음<br>""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<SelAllNoticeVo> getAllNotice(@Valid SelAllNoticeDto dto){
        return service.getKidManagement(dto);

    }

    //-------------------------------- 알림장 상세 조회 --------------------------------

    @GetMapping("/detail")
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
    public SelDetailNoticeVo getDetailNotice(@Valid SelDetailNoticeDto dto){
        return service.getDetailNotice(dto);
    }

//-------------------------------- 알림장 댓글 등록 --------------------------------

    @PostMapping("/comment")
    @Operation(summary = "유치원 알림장 댓글 등록", description = """
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
    public ResVo delNoticeComment(@Valid DelNoticeCommentDto dto){
        return service.delNoticeComment(dto);
    }
}
