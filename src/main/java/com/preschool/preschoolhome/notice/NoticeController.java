package com.preschool.preschoolhome.notice;

import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.notice.model.NoticeInsDto;
import com.preschool.preschoolhome.notice.model.NoticeUpdDto;
import com.preschool.preschoolhome.notice.model.sel.NoticeUpdSelVo;
import io.swagger.v3.oas.annotations.Operation;
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
    public ResVo putUpdNotice(@RequestBody NoticeUpdDto dto){
        return service.updNotice(dto);
    }

    @Operation(summary = "알림장 삭제", description = "알림장 삭제")
    @DeleteMapping
    public ResVo deleteDelNotice(int iteacher,int inotice, int ilevel){
        return service.delNotice(iteacher,inotice,ilevel);
    }
}
