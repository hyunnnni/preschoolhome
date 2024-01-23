package com.preschool.preschoolhome.album;

import com.preschool.preschoolhome.album.model.*;
import com.preschool.preschoolhome.common.utils.ResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/album")
@Tag(name = "활동 앨범", description = "")
public class AlbumController {
    private final AlbumService service;

    //------------------------------------- 활동 앨범 등록 -------------------------------------//
    @Operation(summary = "활동 앨범 등록", description = """
            -1: 실패<br> 1: 성공
            """)
    @PostMapping
    public ResVo postAlbum(@RequestPart List<MultipartFile> pics, @RequestPart AlbumInsDto dto) {
        dto.setAlbumPic(pics);
        return service.postAlbum(dto);
    }


    //------------------------------------- 활동 앨범 전체 조회 -------------------------------------//
    @Operation(summary = "활동 앨범 전체 조회", description = """
            """)
    @GetMapping("/listall")
    public List<AlbumSelVo> getAllAlbum(AlbumSelDto dto) {
        return service.getAllAlbum(dto);
    }



    //------------------------------------- 활동 앨범 메인화면 조회 -------------------------------------//
    @Operation(summary = "활동 앨범 메인화면 조회", description = """
            """)
    @GetMapping("/main")
    public List<AlbumMainVo> getMainAlbum(AlbumMainDto dto) {
        return service.getMainAlbum(dto);
    }


    //------------------------------------- 활동 앨범 상세 조회 -------------------------------------//
    @Operation(summary = "활동 앨범 상세 조회", description = """
            """)
    @GetMapping
    public List<AlbumDetailSelVo> getDetailAlbum(AlbumDetailSelDto dto) {
        return service.getDetailAlbum(dto);
    }


    //------------------------------------- 활동 앨범 글 삭제 -------------------------------------//
    @Operation(summary = "활동 앨범 글 삭제", description = """
            -1: 실패<br> 1: 성공
            """)
    @DeleteMapping
    public ResVo delAlbum(AlbumDelDto dto) {
        return service.delAlbum(dto);
    }


    //------------------------------------- 활동 앨범 댓글 등록 -------------------------------------//
    @Operation(summary = "활동 앨범 댓글 등록", description = """
            -1: 실패<br> 1: 성공
            """)
    @PostMapping("/comment")
    public ResVo postAlbumComment(@RequestBody AlbumCommentInsDto dto) {
        return service.postAlbumComment(dto);
    }


    //------------------------------------- 활동 앨범 댓글 삭제 -------------------------------------//
    @Operation(summary = "활동 앨범 댓글 삭제", description = """
            -1: 실패<br> 1: 성공
            """)
    @DeleteMapping("/comment")
    public ResVo delAlbumComment(AlbumDelCommentDto dto) {
        return service.delAlbumComment(dto);
    }

    //------------------------------------- 활동 앨범 수정 -------------------------------------//
    @Operation(summary = "활동 앨범 수정", description = """
            """)
    @PutMapping
    public ResVo putAlbum(@RequestPart List<MultipartFile> pics,  @RequestPart AlbumUpdDto dto) {
        return service.putAlbum(pics, dto);
    }

    //------------------------------------- 활동 앨범 수정 시 정보 출력-------------------------------------//
    @Operation(summary = "활동 앨범 수정 시 정보 출력")
    @GetMapping("/edit")
    public AlbumDeSelVo albumEdit(int iteacher, int ialbum, @Min(value = 1,message = "권한이 없습니다")int ilevel) {
        return service.albumEdit(iteacher, ialbum);
    }

}

