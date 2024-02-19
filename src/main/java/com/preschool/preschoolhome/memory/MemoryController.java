package com.preschool.preschoolhome.memory;


import com.preschool.preschoolhome.common.security.AuthenticationFacade;
import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.memory.model.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/memory")
@Tag(name = "추억앨범", description = "추억앨범 관리")
public class MemoryController {
    private final MemoryService service;
    private final AuthenticationFacade authenticationFacade;

    @GetMapping
    @Operation(summary = "3차 추억 추억 앨범 전체 조회", description = "")
    public AllMemoryVo getAllMemory(AllSelMemoryDto dto){
        return service.getAllMemory(dto);
    }

    @GetMapping("/detail")
    @Operation(summary = "3차 추억 앨범 상세 조회", description = "")
    public AllSelMemoryVo getMemory(int imemory){
        return service.getMemory(imemory);
    }


    @GetMapping("/edit")
    @Operation(summary = "3차 추억앨범 수정 전 정보 불러오기", description = "추억앨범 수정 전 정보 불러오기")
    public SelMemoryVo getMemoryEdit(@RequestParam int imemory){

        return service.getMemoryEdit(imemory);
    }


    @GetMapping("/tag")
    @Operation(summary = "3차 추억 앨범 작성 시 보내고 싶은 원아 태그에 쓰일 모든 원아 정보", description = """
        여러 원아에게 추억 앨범을 보내고 싶을 때 선택할 수 있는 원아들 정보 조회""")
    public List<MemorySelDto> getFromKids() {
        return service.getFromKids();
    }

    @DeleteMapping
    @Operation(summary = "3차 추억 앨범 글 삭제", description = """
        추억 앨범의 사진, 댓글 모두 삭제""")
    public ResVo delmemory (int imemory) {
        return service.delmemory(imemory);
    }
}
