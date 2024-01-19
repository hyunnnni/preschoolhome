package com.preschool.preschoolhome.notice;

import com.preschool.preschoolhome.common.utils.Const;
import com.preschool.preschoolhome.common.utils.MyFileUtils;
import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.notice.model.NoticeInsDto;
import com.preschool.preschoolhome.notice.model.NoticePicsInsDto;
import com.preschool.preschoolhome.notice.model.NoticeUpdDto;
import com.preschool.preschoolhome.notice.model.sel.NoticeUpdSelVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeMapper mapper;
    private final MyFileUtils myFileUtils;
    //알림장 등록
    ResVo insNotice(List<MultipartFile> pics, NoticeInsDto dto){
        if(dto.getIlevel()<2){
            return new ResVo(Const.FAIL);
        }
        mapper.insNotice(dto);
        String target = "/notice/"+dto.getInotice();

        NoticePicsInsDto picsDto = new NoticePicsInsDto();
        picsDto.setInotice(dto.getInotice());
        for (MultipartFile file: pics) {
            String saveFileNm = myFileUtils.transferTo(file, target);
            picsDto.getPics().add(saveFileNm);
        }
        mapper.insNiticePics(picsDto);
        return new ResVo(Const.SUCCESS);
    }

    public NoticeUpdSelVo noticeEdit(int inotice, int ikid){
        NoticeUpdSelVo vo = mapper.noticeEdit(inotice,ikid);
        List<String> pics = mapper.noticeEditPics(inotice);
        vo.setNoticePics(pics);
        return vo;

    }
    ResVo updNotice(NoticeUpdDto dto){


        return null;
    }

    ResVo delNotice(int iteacher, int inotice, int ilevel){
        if(ilevel != 2){
            return new ResVo(Const.FAIL);
        }
        mapper.delAllNotice(iteacher, inotice);
        mapper.delNotice(iteacher, inotice);
        return new ResVo(Const.SUCCESS);
    }
}
