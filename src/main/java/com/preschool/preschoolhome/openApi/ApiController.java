package com.preschool.preschoolhome.openApi;

import com.preschool.preschoolhome.openApi.medel.DataDto;
import com.preschool.preschoolhome.openApi.medel.DataVo;
import com.preschool.preschoolhome.openApi.medel.TotalDataVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/open")
@Tag(name = "공공 API 소아과", description = "근처 소아기관 정보 조회")
public class ApiController {
    private final ApiService service;

    @Operation(summary = "오픈API 경기도 예방접종 병원")
    @GetMapping("/hospital")
    public TotalDataVo getData(DataDto dto){
        return service.getData(dto);
    }
}
