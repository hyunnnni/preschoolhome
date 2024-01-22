package com.preschool.preschoolhome.album;

import com.preschool.preschoolhome.album.model.*;
import com.preschool.preschoolhome.common.exception.PreschoolErrorCode;
import com.preschool.preschoolhome.common.exception.RestApiException;
import com.preschool.preschoolhome.common.security.AuthenticationFacade;
import com.preschool.preschoolhome.common.utils.MyFileUtils;
import com.preschool.preschoolhome.common.utils.ResVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.preschool.preschoolhome.common.utils.Const.FAIL;
import static com.preschool.preschoolhome.common.utils.Const.SUCCESS;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumMapper mapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;
    // 활동 앨범 전체 조회
    public List<AlbumSelVo> getAllAlbum(AlbumSelDto dto) {
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        if (dto.getIlevel() < 1) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        List<AlbumSelVo> vo = mapper.selAllAlbum(dto);
        return vo;
    }

    // 활동 앨범 메인화면 조회
    public List<AlbumMainVo> getMainAlbum(AlbumMainDto dto) {
        return mapper.selMainAlbum(dto);
    }


    // 활동 앨범 상세 조회
    public List<AlbumDetailSelVo> getDetailAlbum(AlbumDetailSelDto dto) {
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);
        if (dto.getIlevel() < 1) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        List<AlbumDetailSelVo> list = mapper.selDetailAlbum(dto);
        for (AlbumDetailSelVo vo : list) {
            List<String> pics = mapper.selPicsAlbum(dto);
            vo.setAlbumPic(pics);
            List<AlbumAllCommentVo> comment = mapper.selCommentAlbum(dto);
            vo.setAlbumComments(comment);
        }
        return list;
    }


    // 활동앨범 등록
    public ResVo postAlbum(AlbumInsDto dto) {
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);
        if (dto.getIlevel() < 2) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        int AffectedRows = mapper.insAlbum(dto);
        String target = "/album/" + dto.getAlbumTitle();

        AlbumPicsInsDto pdto = new AlbumPicsInsDto();
        pdto.setIalbum(dto.getIalbum());

        for (MultipartFile file : dto.getAlbumPic()) {
            String saveFileNm = myFileUtils.transferTo(file, target);
            pdto.getAlbumPic().add(saveFileNm);
        }
        int picAffectedRows = mapper.insAlbumPic(pdto);
        if (picAffectedRows > 0) {
            return new ResVo(SUCCESS);
        }
        return new ResVo(FAIL);
    }



    // 활동 앨범 삭제
    public ResVo delAlbum (AlbumDelDto dto) {
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);
        if (dto.getIlevel() < 2) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        try {
            mapper.delAlbumCommentPics(dto);
            int delAffectedRows = mapper.delAlbumAll(dto);
            if (delAffectedRows > 0) {
                return new ResVo(SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResVo(FAIL);
    }


    // 활동 앨범 댓글 등록
    public ResVo postAlbumComment(AlbumCommentInsDto dto) {
        int loginUserPk = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        dto.setIparent(loginUserPk);
        dto.setIlevel(level);
        if (dto.getIlevel() < 1) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        try {
            int affectedRows = mapper.insAlbumComment(dto);
            if (affectedRows > 0) {
                return new ResVo(SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResVo(FAIL);
    }

    // 활동 앨범 댓글 삭제
    public ResVo delAlbumComment(AlbumDelCommentDto dto) {
        try {
            int affectedRows = mapper.delAlbumComment(dto);
            if (affectedRows > 0) {
                return new ResVo(SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResVo(FAIL);
    }

}
