package com.preschool.preschoolhome.teacher;

import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.teacher.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;


import java.security.Provider;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher")
@Slf4j
@Tag(name = "관리자 기능 API", description = "관리자 기준 화면 기능 처리")
public class TeacherController {
    private final TeacherService service;

    //-------------------------------- 원아 관리 페이지 조회 --------------------------------
    @GetMapping("/kid")
    @Operation(summary = "원아 관리 페이지 조회", description = """
    리스트 안 result 값이<br>
    -3 : 해당 정보로 조회 시 조회되는 정보 없음<br>
    -2 : 관리자 외 계정으로 접근 시 거부 에러<br>
    0 : 이상 없음<br>""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<SelKidManagementVo> getKidManagement(SelKidManagementDto dto){
        return service.getKidManagement(dto);
    }

    //-------------------------------- 원아 재원 상태 / 반 승급 수정 --------------------------------
    @PatchMapping("/stateorclass")
    @Operation(summary = "원아 재원 상태 / 반 승급 수정", description = """
    result 값이<br>
    -4 : 연결 부모님 계정 삭제 처리 실패<br>
    -3 : 원아 상태 수정 실패<br>
    -2 : 관리자 외 계정으로 접근 시 거부 에러<br>
    -1 : 원아 상태 수정/ 부모님 연결 계정 삭제 실패<br>
    1 이상 : 수정 성공한 원아의 수""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResVo patchKidStateOrClass (@RequestBody UpdKidStateDto dto){
        return service.patchKidStateOrClass(dto);
    }

    //-------------------------------- 학부모 관리 페이지 조회 --------------------------------
    @GetMapping("/parent")
    @Operation(summary = "학부모 관리 페이지 조회", description = """
    리스트 안 result 값이<br>
    -3 : 해당 정보로 조회 시 조회되는 정보 없음<br>
    -2 : 관리자 외 계정으로 접근 시 거부 에러<br>""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<SelParManagementVo> getParentManagement (SelParManagementDto dto){
        return service.getParentManagement(dto);
    }

    //-------------------------------- 학부모 정보 관리자가 삭제 --------------------------------
    @PutMapping("/parent")
    @Operation(summary = "학부모 정보 관리자가 삭제", description = """
    result 값이<br>
    -4 : 부모님 계정 삭제 실패<br>
    -2 : 관리자 외 계정으로 접근 시 거부 에러<br>""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResVo delParent(@RequestBody DelParentDto dto){
        return service.delParent(dto);
    }

    //-------------------------------- 학부모와 원아 연결 끊기 --------------------------------
    @DeleteMapping("/Disconnent")
    @Operation(summary = "학부모와 원아 연결 끊기", description = """
        result 값이<br>
        -2 : 관리자 외 계정으로 접근 시 거부 에러<br>
        -1 : 연결 끊기 실패<br>
        1 : 연결 끊기 성공""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResVo delDisconnect(DelDisconnectDto dto){
        return service.delDisconnect(dto);
    }


}
