package com.preschool.preschoolhome.album;

import com.preschool.preschoolhome.album.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlbumMapper {

    // 활동 앨범 등록
    int insAlbum (AlbumInsDto dto);
    // 활동 앨범 사진 등록
    int insAlbumPic (AlbumPicsInsDto dto);

    // 활동 앨범 전체 조회
    List<AlbumSelVo> selAllAlbum (AlbumSelDto dto);

    // 활동 앨범 상세 조회 (글)
    List<AlbumDetailSelVo> selDetailAlbum (AlbumDetailSelDto dto);
    // 활동 앨범 상세 조회 (사진)
    List<String> selPicsAlbum (AlbumDetailSelDto dto);
    // 활동 앨범 상세 조회 (댓글)
    List<AlbumAllCommentVo> selCommentAlbum (AlbumDetailSelDto dto);

    // 활동 앨범 수정
    AlbumDeSelVo selAlbumContent(int iteacher, int ialbum);
    List<String> albumEditPics(int ialbum);
    int updAlbum (AlbumUpdDto dto);
    int delAlbumPic(int ialbum);

    // 활동 앨범 삭제 (사진, 댓글)
    int delAlbumCommentPics (AlbumDelDto dto);
    // 활동 앨범 삭제 (글)
    int delAlbumAll (AlbumDelDto dto);


    // 앨범 댓글 등록
    int insAlbumComment (AlbumCommentInsDto dto);
    // 앨번 댓글 삭제
    int delAlbumComment (AlbumDelCommentDto dto);

}
