package com.preschool.preschoolhome.memory;


import com.preschool.preschoolhome.common.security.AuthenticationFacade;
import com.preschool.preschoolhome.common.utils.MyFileUtils;

import com.preschool.preschoolhome.memory.model.AllMemoryVo;
import com.preschool.preschoolhome.memory.model.AllSelMemoryDto;
import com.preschool.preschoolhome.memory.model.AllSelMemoryVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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



}
