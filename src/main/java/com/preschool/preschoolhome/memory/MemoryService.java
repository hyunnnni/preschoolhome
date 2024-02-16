package com.preschool.preschoolhome.memory;


import com.preschool.preschoolhome.common.exception.AuthErrorCode;
import com.preschool.preschoolhome.common.exception.CommonErrorCode;
import com.preschool.preschoolhome.common.exception.PreschoolErrorCode;
import com.preschool.preschoolhome.common.exception.RestApiException;
import com.preschool.preschoolhome.common.security.AuthenticationFacade;
import com.preschool.preschoolhome.common.utils.Const;
import com.preschool.preschoolhome.common.utils.MyFileUtils;

import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.memory.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemoryService {
    private final MemoryMapper mapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;

    public AllMemoryVo allMemory(AllSelMemoryDto dto){
        List<String> roles = authenticationFacade.getRoles();
        AllMemoryVo vo = new AllMemoryVo();
        if(roles.get(0).equals("TEACHER") || roles.get(0).equals("ADMIN")){
            List<AllSelMemoryVo> list = mapper.allMemoryTea(dto);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setMemoryComment(mapper.iMemoryComment(list.get(i).getImemory()));
                list.get(i).setIkids(mapper.iMemoryIkid(list.get(i).getImemory()));
            }
            vo.setList(list);
            vo.setImemoryCnt(mapper.allMemoryTeaCnt(dto));
        }
        if(roles.get(0).equals("USER") || roles.get(0).equals("GRADUATE")){
            List<AllSelMemoryVo> list = mapper.allMemoryPar(dto);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setMemoryComment(mapper.iMemoryComment(list.get(i).getImemory()));
                list.get(i).setIkids(mapper.iMemoryIkid(list.get(i).getImemory()));
            }
            vo.setList(list);
            vo.setImemoryCnt(mapper.allMemoryParCnt(dto));
        }
        return vo;
    }


    public SelMemoryVo selMemory(int imemory){
        //한개의 앨범안에 여러개의 사진이 들어가야함
        String exist = mapper.selImemory(imemory);
        if(exist ==null){
            throw new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION);
        }
        if(imemory < 0 ){
            throw new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION);
        }
        SelMemoryVo memory = mapper.selMemory(imemory);
        List<String> pics = mapper.selMemoryPic(imemory);
        memory.setMemoryPic(pics);
        //한개의 memory 안에 여러개의 사진을 넣어서 return  해줘야함

        return memory;
    }


    //------------------------------------- 추억 앨범 글 작성 시 전체 원아 조회 -------------------------------------
    public List<MemorySelDto> getFromKids() {
        return mapper.getFromKids();
    }

    //------------------------------------- 추억 앨범 글 삭제 -------------------------------------
    @Transactional
    public ResVo delmemory (int imemory) {
        int level = authenticationFacade.getLevelPk();

        if (level < Const.TEACHER) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }

        try {
            mapper.delMemoryAll(imemory);
            int delMemory = mapper.delMemory(imemory);
            if (delMemory > 0) {
                return new ResVo(Const.SUCCESS);
            }
        } catch (Exception e) {
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
        return new ResVo(Const.FAIL);
    }

}
