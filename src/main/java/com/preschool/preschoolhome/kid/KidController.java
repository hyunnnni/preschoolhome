package com.preschool.preschoolhome.kid;

import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.kid.model.*;
import com.preschool.preschoolhome.kid.model.KidDetailEditVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/kid")
@Tag(name = "원아", description = "원아 관리")
public class KidController {
    private final KidService service;

    @Operation(summary = "원아 마이페이지", description = "원아 해당 년도 마이페이지 조회")
    @GetMapping("/{year}/{ikid}")
    public KidProfileVo getKidProfile(@PathVariable int year,@PathVariable int ikid, int ilevel){
        return service.kidProfile(year, ikid, ilevel);
    }

    @Operation(summary = "원아 식별코드 수정", description = "원아 식별코드 수정")
    @PatchMapping("/code")
    public ResVo patchKidCode(int ikid, int ilevel){
        return service.kidCode(ikid, ilevel);
    }

    @Operation(summary = "원아 등록", description = "원아 등록")
    @PostMapping
    public KidInsVo postKidSignup(@RequestPart MultipartFile pic, @RequestPart KidInsDto dto){
        return service.kidSignup(pic, dto);
    }

    @Operation(summary = "원아 발달사항 등록", description = "원아 발달사항 등록")
    @PostMapping("/detail")
    public ResVo postKidInsDetail(@RequestBody List<KidDetailInsDto> dto){
        return service.kidInsDetail(dto);
    }

    @Operation(summary = "원아 발달사항 수정", description = "원아 발달사항 수정")
    @PutMapping("/detail")
    public ResVo putKidUpdDetail(@RequestBody List<KidDetailUpdDto> dto){
        return service.kidUpdDetail(dto);
    }

    @Operation(summary = "원아 발달사항 수정 시 기존 데이터 조회", description = "원아 발달사항 수정 시 기존 데이터 조회")
    @GetMapping("/detail/edit/{ikid}")
    public KidDetailEditVo getKidDetailEdit(@PathVariable int ikid, int ilevel, int year){
        return service.kidDetailEdit(ikid,ilevel,year);
    }

    @Operation(summary = "원아 프로필 수정", description = "원아 프로필 수정")
    @PutMapping
    public ResVo putKidProfile(@RequestPart MultipartFile pic, @RequestPart  KidUpdDto dto){
        return service.kidUpdProfile(pic, dto);
    }

    @Operation(summary = "원아 프로필 수정 시 기존 데이터 조회", description = "원아 프로필 수정 시 기존 데이터 조회")
    @GetMapping("/edit/{ikid}")
    public KidProfileEditVo getKidEdit(@PathVariable int ikid, int ilevel){
        return service.kidEdit(ikid,ilevel);
    }

    @Operation(summary = "졸업한 지 10년 된  원아 전체 삭제", description = "졸업한 지 10년 된  원아 전체 삭제")
    @DeleteMapping
    public ResVo delAllGraduateKid(int ilevel){
        return service.allGraduateKid(ilevel);
    }



}
