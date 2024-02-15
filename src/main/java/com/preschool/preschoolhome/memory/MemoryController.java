package com.preschool.preschoolhome.memory;


import com.preschool.preschoolhome.memory.model.AllMemoryVo;
import com.preschool.preschoolhome.memory.model.AllSelMemoryDto;

import com.preschool.preschoolhome.memory.model.AllSelMemoryVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/memory")
@Tag(name = "추억앨범", description = "추억앨범 관리")
public class MemoryController {
    private final MemoryService service;

    @GetMapping
    public AllMemoryVo getAllMemory(AllSelMemoryDto dto){
        return service.allMemory(dto);
    }

    @GetMapping("/detail")
    public AllSelMemoryVo getMemory(int imemory){
        return null;
    }
}
