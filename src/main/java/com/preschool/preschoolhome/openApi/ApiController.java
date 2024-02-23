package com.preschool.preschoolhome.openApi;

import com.preschool.preschoolhome.openApi.medel.DataDto;
import com.preschool.preschoolhome.openApi.medel.DataVo;
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
public class ApiController {
    private final ApiService service;


    @GetMapping("/hospital")
    public List<DataVo> getData(DataDto dto){
        return service.getData(dto);
    }
}
