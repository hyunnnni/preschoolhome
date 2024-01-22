package com.preschool.preschoolhome.kid;

import com.preschool.preschoolhome.common.security.AuthenticationFacade;
import com.preschool.preschoolhome.common.utils.MyFileUtils;
import com.preschool.preschoolhome.kid.model.KidGrowth;
import com.preschool.preschoolhome.kid.model.KidParent;
import com.preschool.preschoolhome.kid.model.KidProfileVo;
import org.checkerframework.checker.units.qual.K;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(KidService.class)
class KidServiceTest {

    @MockBean
    private KidMapper mapper;
    @MockBean
    private MyFileUtils myFileUtils;
    @MockBean
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private KidService service;

    @Test
    void kidProfile() {
        KidProfileVo vo = new KidProfileVo();
        vo.setResult(1);
        vo.setKidNm("곽경록");

        when(mapper.kidProfile(anyInt())).thenReturn(vo);

        KidParent kidParent = new KidParent();
        kidParent.setUid("아이디");
        KidParent kidParent2 = new KidParent();
        kidParent2.setUid("아이디2");
        when(mapper.kidParent(anyInt())).thenReturn(List.of(kidParent, kidParent2));

        KidGrowth kidGrowth = new KidGrowth();
        kidGrowth.setHeight(200);
        when(mapper.kidGrowth(anyInt(), anyInt())).thenReturn(List.of(kidGrowth));


        KidProfileVo kidProfileVo = service.kidProfile(1, 1);


        List<KidGrowth> growths = kidProfileVo.getGrowths();
        KidGrowth kidGrowth1 = growths.get(0);
        int height = kidGrowth1.getHeight();

        Assertions.assertEquals(height, 200);


    }

    @Test
    void kidSignup() {
    }

    @Test
    void kidInsDetail() {
    }

    @Test
    void kidUpdDetail() {
    }

    @Test
    void kidDetailEdit() {
    }

    @Test
    void kidUpdProfile() {
    }

    @Test
    void kidEdit() {
    }

    @Test
    void allGraduateKid() {
    }
}