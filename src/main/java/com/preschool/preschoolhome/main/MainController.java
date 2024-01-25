package com.preschool.preschoolhome.main;

import com.preschool.preschoolhome.main.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/main")
@Tag(name = "메인 화면 API", description = "메인 화면 관련")
public class MainController {
    private final MainService service;

    //-------------------------------------  메인화면 조회 -------------------------------------//
    @Operation(summary = "메인화면 조회")
    @Valid
    @GetMapping
    public MainVo getMain(@RequestParam
                               @Positive(message = "잘못된 값입니다")
                               @Schema(title = "페이지")
                               int page) {
        MainDto dto = new MainDto();
        dto.setPage(page);
        return service.getMain(dto);
    }

}

