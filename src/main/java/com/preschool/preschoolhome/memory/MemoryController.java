package com.preschool.preschoolhome.memory;


import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.memory.model.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/memory")
@Tag(name = "추억앨범", description = "추억앨범 관리")
public class MemoryController {
    private final MemoryService service;


    //-------------------------------- 추억 앨범 전체 조회 --------------------------------
    @GetMapping
    @Operation(summary = "3차 추억 앨범 전체 조회")
    public AllMemoryVo getAllMemory(AllSelMemoryDto dto){
        return service.getAllMemory(dto);
    }

    //-------------------------------- 추억 앨범 상세 조회 --------------------------------
    @GetMapping("/detail")
    @Operation(summary = "3차 추억 앨범 상세 조회")
    public AllSelMemoryVo getMemory(int imemory){
        return service.getMemory(imemory);
    }


    //-------------------------------- 추억앨범 수정 전 정보 불러오기 --------------------------------
    @GetMapping("/edit")
    @Operation(summary = "3차 추억앨범 수정 전 정보 불러오기", description = "추억앨범 수정 전 정보 불러오기")
    public SelMemoryVo getMemoryEdit(@RequestParam int imemory){

        return service.getMemoryEdit(imemory);
    }

    //-------------------------------- 추억 앨범 작성 시 보내고 싶은 원아 태그에 쓰일 모든 원아 정보 --------------------------------
    @GetMapping("/tag")
    @Operation(summary = "3차 추억 앨범 작성 시 보내고 싶은 원아 태그에 쓰일 모든 원아 정보", description = """
        여러 원아에게 추억 앨범을 보내고 싶을 때 선택할 수 있는 원아들 정보 조회""")
    public List<MemorySelVo> getFromKids() {
        return service.getFromKids();
    }

    //-------------------------------- 추억 앨범 글 삭제 --------------------------------
    @DeleteMapping
    @Operation(summary = "3차 추억 앨범 글 삭제", description = """
        추억 앨범의 사진, 댓글 모두 삭제""")
    public ResVo delmemory (@RequestParam int imemory) {
        return service.delMemory(imemory);
    }


    //-------------------------------- 추억 앨범 등록 --------------------------------
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "3차 추억 앨범 등록", description = """
            선생님만 등록 가능<br>
            사진 최대 20장 등록 가능<br>
            사진 업로드 필수""")
    public ResVo postMemory(@RequestPart List<MultipartFile> pics, @RequestPart InsMemoryDto dto){
        return service.postMemory(pics, dto);
    }

    //-------------------------------- 추억 앨범 댓글 등록--------------------------------
    @PostMapping("/comment")
    @Operation(summary = "추억앨범 댓글 등록", description = "<strong>추억앨범 댓글 등록, 푸시기능</strong><br><br>")
    public ResVo postMemoryComment(@RequestBody InsCommentDto dto){
        return service.postMemoryComment(dto);
    }


    //-------------------------------- 추억 앨범 댓글 삭제 --------------------------------
    @DeleteMapping("/comment")
    @Valid
    @Operation(summary = "3차 추억 앨범 댓글 삭제")
    public ResVo delMemoryComment(@RequestParam
                                  @Positive(message="잘못된 값입니다")
                                  @Schema(title = "삭제하고 싶은 댓글 pk")
                                  int imemoryComment,
                                  @RequestParam(required = false, defaultValue = "0")
                                  @PositiveOrZero(message = "잘못된 값입니다")
                                  @Schema(title = "삭제를 원하는 유저의 pk(관리자일 때)")
                                  int iteacher,
                                  @RequestParam(required = false, defaultValue = "0")
                                  @PositiveOrZero(message = "잘못된 값입니다")
                                  @Schema(title = "삭제를 원하는 유저의 pk(학부모일 때)")
                                  int iparent){
        DelMemoryCommentDto dto = new DelMemoryCommentDto();
        dto.setImemoryComment(imemoryComment);
        dto.setIteacher(iteacher);
        dto.setIparent(iparent);
        return service.delMemoryComment(dto);
    }

    //-------------------------------- 추억 앨범 글 수정 --------------------------------
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "3차 추억 앨범 글 수정", description = """
        추억 앨범 글, 사진, 원아 수정 및 새로 추가한 원아들에게 알림 푸시""")
    public ResVo putMemory(@RequestPart(required = false) List<MultipartFile> pics, @RequestPart MemoryUpdDto dto) {
        return service.putMemory(pics, dto);
    }
}
