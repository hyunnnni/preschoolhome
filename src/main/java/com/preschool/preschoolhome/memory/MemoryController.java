package com.preschool.preschoolhome.memory;


import com.preschool.preschoolhome.common.exception.AuthErrorCode;
import com.preschool.preschoolhome.common.exception.RestApiException;
import com.preschool.preschoolhome.common.security.AuthenticationFacade;
import com.preschool.preschoolhome.memory.model.AllMemoryVo;
import com.preschool.preschoolhome.memory.model.AllSelMemoryDto;

import com.preschool.preschoolhome.memory.model.AllSelMemoryVo;
import com.preschool.preschoolhome.memory.model.SelMemoryVo;
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
    public AllMemoryVo getAllMemory(AllSelMemoryDto dto){
        return service.allMemory(dto);
    }

    @GetMapping("/detail")
    public AllSelMemoryVo getMemory(int imemory){
        return null;
    }


    @GetMapping("/eidt")
    @Operation(summary = "추억앨범 정보 불러오기", description = "추억앨범 수정 전 정보 불러오기")
    public SelMemoryVo getMemoryEdit(@RequestParam int imemory){
//        List<String> roles = authenticationFacade.getRoles();
//        if(!(roles.get(0).equals("admin"))){
//            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
//        }
        return service.selMemory(imemory);
    }
}
