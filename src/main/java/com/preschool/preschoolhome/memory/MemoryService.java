package com.preschool.preschoolhome.memory;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.preschool.preschoolhome.common.exception.AuthErrorCode;
import com.preschool.preschoolhome.common.exception.CommonErrorCode;
import com.preschool.preschoolhome.common.exception.PreschoolErrorCode;
import com.preschool.preschoolhome.common.exception.RestApiException;
import com.preschool.preschoolhome.common.security.AuthenticationFacade;
import com.preschool.preschoolhome.common.utils.Const;
import com.preschool.preschoolhome.common.utils.MyFileUtils;

import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.entity.*;
import com.preschool.preschoolhome.memory.model.*;
import com.preschool.preschoolhome.notice.model.SelNoticeOtherTokens;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.preschool.preschoolhome.common.utils.Const.SUCCESS;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemoryService {
    private final MemoryMapper mapper;
    private final MemoryRepository repository;
    private final TeacherRepository teacherRepository;
    private final MemoryCommentRepository commentRepository;
    private final KidRepository kidRepository;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;
    private final ObjectMapper objMapper;
    private final MemoryRoomRepository memoryRoomRepository;
    private final MemoryAlbumRepository albumRepository;

    //------------------------------------- 추억 앨범 전체 조회 ------------------------------
    @Transactional
    public AllMemoryVo getAllMemory(AllSelMemoryDto dto) {
        int level = authenticationFacade.getLevelPk();
        int iuser = authenticationFacade.getLoginUserPk();

        final List<MemoryEntity> list = repository.selMemoryAll(dto);

        final List<MemoryAlbumEntity> picList = repository.selMemoryPicsAll(list);

        final List<MemoryCommentEntity> cmtList = repository.selMemoryCommentAll(list);


        AllMemoryVo vo = new AllMemoryVo();

        List<AllSelMemoryVo> vo1 = list.stream().map(item -> {
            List<MemoryRoomEntity> memoryroomList = memoryRoomRepository.findAllByMemoryEntity(item);

            List<MemoryCommentVo> eachCommentList = cmtList.stream()
                    .filter(cmt -> cmt.getMemoryEntity().getImemory() == item.getImemory())
                    .map(cmt ->
                            MemoryCommentVo.builder()
                                    .imemoryComment(cmt.getImemoryComment())
                                    .memoryComment(cmt.getMemoryComment())
                                    .createdAt(cmt.getCreatedAt().toString())
                                    .build()
                    ).collect(Collectors.toList());
            List<String> eachPicList = picList.stream()
                    .filter(pic -> pic.getMemoryEntity().getImemory() == item.getImemory())
                    .map(pic -> pic.getMemoryPic())
                    .collect(Collectors.toList());
            List<KidsVo> kids = memoryroomList.stream().map(kid -> {
                return KidsVo.builder()
                        .ikid(kid.getKidEntity().getIkid())
                        .kidNm(kid.getKidEntity().getKidNm())
                        .build();
            }).collect(Collectors.toList());


            return AllSelMemoryVo.builder()
                    .imemory(item.getImemory())
                    .memoryTitle(item.getTitle())
                    .memoryContents(item.getContents())
                    .createdAt(item.getCreatedAt().toString())
                    .iteacher(item.getTeacherEntity().getIteacher())
                    .teacherNm(item.getTeacherEntity().getTeacherNm())
                    .memoryPic(eachPicList)
                    .kids(kids)
                    .memoryComments(eachCommentList)
                    .build();
        }).collect(Collectors.toList());

        vo.setList(vo1);
        vo.setImemoryCnt(list.size());
        return vo;
    }


    /*public AllMemoryVo getAllMemory(AllSelMemoryDto dto) {
        int level = authenticationFacade.getLevelPk();
        AllMemoryVo vo = new AllMemoryVo();
        if (level == 2 || level == 3) {
            List<AllSelMemoryVo> list = mapper.allMemoryTea(dto);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setMemoryPic(mapper.iMemoryPic(list.get(i).getImemory()));
                list.get(i).setMemoryComments(mapper.memoryComment(list.get(i).getImemory()));
                list.get(i).setKids(mapper.iMemoryIkid(list.get(i).getImemory()));
            }
            vo.setList(list);
            vo.setImemoryCnt(mapper.allMemoryTeaCnt(dto));
        } else
        if (level == 1 || level == 4) {
            List<AllSelMemoryVo> list = mapper.allMemoryPar(dto);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setMemoryPic(mapper.iMemoryPic(list.get(i).getImemory()));
                list.get(i).setMemoryComments(mapper.memoryComment(list.get(i).getImemory()));
                list.get(i).setKids(mapper.iMemoryIkid(list.get(i).getImemory()));
            }
            vo.setList(list);
            vo.setImemoryCnt(mapper.allMemoryParCnt(dto));
        }

        return vo;
    }*/

    //-------------------------------- 추억 앨범 상세 조회 JPA --------------------------------
    @Transactional
    public AllSelMemoryVo getMemory(int imemory) {
        int pk = authenticationFacade.getLoginUserPk();
        String role = authenticationFacade.getRole();

        if(role.equals("TEACHER")||role.equals("ADMIN")){

        }

        MemoryEntity memory = repository.findAllByImemory(imemory);

        List<String> pics = memory.getMemoryAlbumEntityList().stream()
                .map(pic ->
                        pic.getMemoryPic())
                .collect(Collectors.toList());

        List<MemoryRoomEntity> memoryRoomList = memoryRoomRepository.findAllByMemoryEntity(memory);

        List<KidsVo> kids = memoryRoomList.stream()
                .map(memoryroom -> {
                    return KidsVo.builder()
                            .ikid(memoryroom.getKidEntity().getIkid())
                            .kidNm(memoryroom.getKidEntity().getKidNm())
                            .build();
                })
                .collect(Collectors.toList());

        List<MemoryCommentEntity> memoryCommentList = commentRepository.findAllByMemoryEntity(memory);

        List<MemoryCommentVo> commentVos = memoryCommentList.stream()
                .map(cmt -> {
                    MemoryCommentVo vo = MemoryCommentVo.builder()
                            .imemoryComment(cmt.getImemoryComment())
                            .createdAt(cmt.getCreatedAt().toString())
                            .memoryComment(cmt.getMemoryComment())
                            .build();

                    vo.setTeacherNm(cmt.getTeacher() == null ? null : cmt.getTeacher().getTeacherNm());
                    vo.setParentNm(cmt.getParent() == null ? null : cmt.getParent().getParentNm());
                    return vo;
                }).collect(Collectors.toList());

        AllSelMemoryVo vo = AllSelMemoryVo
                .builder()
                .imemory(memory.getImemory())
                .memoryTitle(memory.getTitle())
                .memoryContents(memory.getContents())
                .memoryPic(pics)
                .iteacher(memory.getTeacherEntity().getIteacher())
                .teacherNm(memory.getTeacherEntity().getTeacherNm())
                .createdAt(memory.getCreatedAt().toString())
                .kids(kids)
                .memoryComments(commentVos)
                .build();

        return vo;
    }

    /*public AllSelMemoryVo getMemory(int imemory) {
        int pk = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        //if(level == 1 || level ==4){
            //pk로 허용됐는지 조회 후 뜨로우
        //}
        AllSelMemoryVo vo = mapper.memory(imemory);
        vo.setMemoryPic(mapper.iMemoryPic(imemory));
        vo.setKids(mapper.iMemoryIkid(imemory));
        List<MemoryCommentVo> cmtList = mapper.memoryComment(imemory);
        //for (int i = 0; i < cmtList.size(); i++) {
            //cmtList.get(i).setWriterNm();
        //}
        vo.setMemoryComments(cmtList);


        return vo;
    }
*/
    //    //------------------------------------- 추억 앨범 수정시 원래 정보 불러오기 ------------------------------
//    public SelMemoryVo getMemoryEdit(int imemory){
//
//        List<String> roles = authenticationFacade.getRoles();
//
////        if(!(roles.get(0).equals("ADMIN") || roles.get(0).equals("TEACHER"))){
////            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
////        }
//
//        Integer pk = mapper.selImemory(imemory);
//        if(pk ==null){
//            throw new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION);
//        }
//        if(imemory < 0 ){
//            throw new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION);
//        }
//        SelMemoryVo memory = mapper.selMemory(imemory);
//        List<String> pics = mapper.selMemoryPic(imemory);
//        memory.setMemoryPic(pics);
//        memory.setIkid(mapper.selMemoryKid(imemory));
//
//
//
//
//        return memory;
//    }
    //------------------------------------- 추억 앨범 수정시 원래 정보 불러오기 ------------------------------
    public SelMemoryVo getMemoryEdit(int imemory) {

        Optional<MemoryEntity> optEntity = repository.findById(imemory);
        MemoryEntity entity = optEntity.orElseThrow(() -> new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION));
        List<MemoryAlbumEntity> pics = entity.getMemoryAlbumEntityList();

        SelMemoryVo vo = new SelMemoryVo();
        vo.setMemoryTitle(entity.getTitle());
        vo.setMemoryContents(entity.getContents());

        List<Integer> ikidList = optEntity.map(MemoryEntity::getMemoryRoomEntityList)
                .orElseThrow(() -> new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION))
                .stream()
                .map(kidRoom -> kidRoom.getKidEntity().getIkid().intValue())
                .collect(Collectors.toList());
        vo.setIkid(ikidList);

        List<String> memoryPicList = pics.stream()
                .map(MemoryAlbumEntity::getMemoryPic)
                .collect(Collectors.toList());
        vo.setMemoryPic(memoryPicList);

        return vo;
    }


    //------------------------------------- 추억 앨범 글 작성 시 전체 원아 조회 -------------------------------------
    public List<MemorySelVo> getFromKids() {


        List<MemorySelVo> list = kidRepository.findAll().stream().map(item ->
                MemorySelVo.builder()
                        .ikid(item.getIkid())
                        .iclass(item.getClassEntity().getIclass())
                        .kidNm(item.getKidNm())
                        .build()).collect(Collectors.toList());

        //List<MemorySelVo> list = mapper.getFromKids();
        return list;
    }

    //------------------------------------- 추억 앨범 글 삭제 -------------------------------------
    @Transactional
    public ResVo delMemory(int imemory) {
        int level = authenticationFacade.getLevelPk();
        if (level < Const.TEACHER) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }

        int selDel = mapper.selDelAlbum(imemory);
        if (selDel == 0) {
            throw new RestApiException(AuthErrorCode.NO_INFORMATION);
        }



        MemoryEntity memoryEntity = repository.getReferenceById(imemory);
        memoryRoomRepository.deleteByMemoryEntity(imemory);
        commentRepository.deleteByMemoryEntity(imemory);
        albumRepository.deleteByMemoryEntity(imemory);
        repository.delete(memoryEntity);
        return new ResVo(SUCCESS);

    }

    /*//------------------------------------- 추억 앨범 글 등록 JPA -------------------------------------
    @Transactional
    public ResVo postMemory(List<MultipartFile> pics, InsMemoryDto dto){

        int iuser = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        String loginUserNm = authenticationFacade.getUserNm();

        if(level == Const.TEACHER || level == Const.BOSS){
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        if (pics.size()>Const.ALBUM_PIC){
            throw new RestApiException(AuthErrorCode.MANY_PIC);
        }
        if (pics.size() == Const.ZERO) {
            throw new RestApiException(AuthErrorCode.PICS_NULL);
        }

        TeacherEntity teacherEntity= teacherRepository.getReferenceById(authenticationFacade.getLoginUserPk());

        MemoryEntity memoryEntity = new MemoryEntity();
        memoryEntity.setContents(dto.getMemoryContent());
        memoryEntity.setTeacherEntity(teacherEntity);

        repository.save(memoryEntity);


        InsRoomInviteProcDto pdto = InsRoomInviteProcDto.builder()
                .imemory(dto.getImemory())
                .ikids(dto.getIkids())
                .build();

        int invite = mapper.insMemoryRoomInvite(pdto);
        if (invite == Const.ZERO) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }

        InsMemoryPicsDto picsDto = new InsMemoryPicsDto();
        picsDto.setImemory(memoryEntity.getImemory());
        String target = "/memory/" + memoryEntity.getImemory();

        for (MultipartFile file : pics) {
            String saverFileNm = myFileUtils.transferTo(file, target);
            picsDto.getMemoryPics().add(saverFileNm);
        }
        List<MemoryAlbumEntity> memoryAlbumEntityList = picsDto.getMemoryPics()
                .stream()
                .map(item -> MemoryAlbumEntity.builder()
                        .memoryEntity(memoryEntity)
                        .memoryPic(item)
                        .build()
                ).collect(Collectors.toList());
        memoryEntity.getMemoryAlbumEntityList().addAll(memoryAlbumEntityList);



        LocalDateTime now = LocalDateTime.now(); // 현재 날짜 구하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 포맷 정의
        String createdAt = now.format(formatter); // 포맷 적용

        List<SelMemoryOtherTokens> otherTokens = new ArrayList<>();
        otherTokens = mapper.selParFirebaseByLoginUser(dto.getIkids());

        if (otherTokens != null) {
            try {

                otherTokens.removeAll(Collections.singletonList(null));
                for (SelMemoryOtherTokens token : otherTokens) {

                    MemoryPushVo pushVo = new MemoryPushVo();
                    pushVo.setMemoryTitle(dto.getMemoryTitle());
                    pushVo.setWriterIuser(iuser);
                    pushVo.setCreatedAt(createdAt);
                    pushVo.setIkid(token.getIkid());
                    pushVo.setKidNm(token.getKidNm());
                    pushVo.setImemory(dto.getImemory());
                    pushVo.setKidNm(loginUserNm);

                    String body = objMapper.writeValueAsString(pushVo);
                    log.info("body: {}", body);
                    Notification noti = Notification.builder()
                            .setTitle("postMemory")
                            .setBody(body)
                            .build();

                    Message message = Message.builder()
                            .setToken(token.getFirebaseToken())
                            .setNotification(noti)
                            .build();

                    FirebaseMessaging.getInstance().sendAsync(message);
                }
            } catch (Exception e) {
                throw new RestApiException(AuthErrorCode.PUSH_FAIL);
            }
        }

        return new ResVo(dto.getImemory());
    }*/
//------------------------------------- 추억 앨범 글 등록 -------------------------------------
    @Transactional
    public ResVo postMemory(List<MultipartFile> pics, InsMemoryDto dto) {

        int iuser = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        String loginUserNm = authenticationFacade.getUserNm();

        if (level != Const.TEACHER && level != Const.BOSS) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        if (pics.size() > Const.ALBUM_PIC) {
            throw new RestApiException(AuthErrorCode.MANY_PIC);
        }
        if (pics.size() == Const.ZERO) {
            throw new RestApiException(AuthErrorCode.PICS_NULL);
        }

        dto.setIteacher(iuser);
        int result = mapper.insMemory(dto);
        if (result == Const.ZERO) {
            throw new RestApiException(AuthErrorCode.POST_FAIL);
        }

        InsRoomInviteProcDto pdto = InsRoomInviteProcDto.builder()
                .imemory(dto.getImemory())
                .ikids(dto.getIkids())
                .build();

        int invite = mapper.insMemoryRoomInvite(pdto);
        if (invite == Const.ZERO) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }


        InsMemoryPicsDto picsDto = new InsMemoryPicsDto();
        picsDto.setImemory(dto.getImemory());
        String target = "/memory/" + dto.getImemory();

        for (MultipartFile file : pics) {
            String saverFileNm = myFileUtils.transferTo(file, target);
            picsDto.getMemoryPics().add(saverFileNm);
        }
        int picResult = mapper.insMemoryPic(picsDto);

        if (picResult < 1) {
            throw new RestApiException(AuthErrorCode.PICS_FAIL);
        }

        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        String createdAt = nowInKorea.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<SelMemoryOtherTokens> otherTokens = new ArrayList<>();
        otherTokens = mapper.selParFirebaseByLoginUser(dto.getIkids());

        if (otherTokens != null) {
            try {

                otherTokens.removeAll(Collections.singletonList(null));
                for (SelMemoryOtherTokens token : otherTokens) {

                    MemoryPushVo pushVo = new MemoryPushVo();
                    pushVo.setMemoryTitle(dto.getMemoryTitle());
                    pushVo.setWriterIuser(iuser);
                    pushVo.setCreatedAt(createdAt);
                    pushVo.setIkid(token.getIkid());
                    pushVo.setKidNm(token.getKidNm());
                    pushVo.setImemory(dto.getImemory());

                    String body = objMapper.writeValueAsString(pushVo);
                    log.info("body: {}", body);
                    Notification noti = Notification.builder()
                            .setTitle("postMemory")
                            .setBody(body)
                            .build();

                    Message message = Message.builder()
                            .setToken(token.getFirebaseToken())
                            .setNotification(noti)
                            .build();

                    FirebaseMessaging.getInstance().sendAsync(message);
                }
            } catch (Exception e) {
                throw new RestApiException(AuthErrorCode.PUSH_FAIL);
            }
        }

        return new ResVo(dto.getImemory());
    }


    //---------------------------- 추억앨범 댓글등록 push기능 --------------------------------
    @Transactional
    public ResVo postMemoryComment(InsCommentDto dto) {
        if ((dto.getIparent() == 0) && dto.getIteacher() == 0 ||
                (dto.getIteacher() > 0 && dto.getIteacher() > 0)) {
            throw new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION);
        }
        int writerIuser = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        String loginUserNm = authenticationFacade.getUserNm();
        dto.setIlevel(level);

        int result = mapper.insComment(dto);
        if (result == 0) {
            return new ResVo(-1);
        }

        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        String createdAt = nowInKorea.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<SelMemoryOtherTokens> otherTokens = null;
        if (level == Const.PARENT) {
            otherTokens = mapper.selParFirebaseByLoginUserComment(dto.getImemoryComment());
        }
        if (level == Const.TEACHER || level == Const.BOSS) {
            otherTokens = mapper.selTeaFirebaseByLoginUserComment(dto.getImemoryComment());
        }
        try {
            if (otherTokens != null) {
                otherTokens.removeAll(Collections.singletonList(null));
                for (SelMemoryOtherTokens token : otherTokens) {

                    MemoryCommentPushVo pushVo = new MemoryCommentPushVo();
                    pushVo.setImemory(dto.getImemory());
                    pushVo.setIkid(token.getIkid());
                    pushVo.setKidNm(token.getKidNm());
                    pushVo.setMemoryComment(dto.getMemoryComment());
                    pushVo.setCmtWriterIuser(writerIuser);
                    pushVo.setCmtWriterNm(loginUserNm);
                    pushVo.setCmtCreatedAt(createdAt);

                    String body = objMapper.writeValueAsString(pushVo);
                    log.info("body: {}", body);
                    Notification noti = Notification.builder()
                            .setTitle("moemoryComment")
                            .setBody(body)
                            .build();

                    Message message = Message.builder()
                            .setToken(token.getFirebaseToken())
                            .setNotification(noti)
                            .build();

                    FirebaseMessaging.getInstance().sendAsync(message);
                }
            }
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.PUSH_FAIL);
        }
        return new ResVo(result);

    }


    //------------------------------------- 추억 앨범 댓글 삭제 JPA-------------------------------------
    @Transactional
    public ResVo delMemoryComment(DelMemoryCommentDto dto) {

        if ((dto.getIparent() == 0 && dto.getIteacher() == 0) ||
                (dto.getIparent() > 0 && dto.getIteacher() > 0)) {
            throw new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION);
        }

        /*int result = mapper.delMemoryComment(dto);

        if (result == 0) {
            return new ResVo(Const.FAIL);
        }
        return new ResVo(result);*/

        try {
            ResVo resVo = commentRepository.delMemoryComment(dto);
            return resVo;

        } catch (Exception e) {
            return new ResVo(Const.FAIL);
        }
    }


    //------------------------------------- 추억 앨범 글 수정 -------------------------------------
    @Transactional
    public ResVo putMemory(List<MultipartFile> pics, MemoryUpdDto dto) {
        int writerIuser = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        String loginUserNm = authenticationFacade.getUserNm();
        if (level < Const.TEACHER) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }

        List<Integer> kids = mapper.selMemoryKid(dto.getImemory()); //전체 아이들 셀렉
        if (kids.size() > 0) {
            // 글 수정
            int updMemory = mapper.updMemory(dto);
            if (updMemory == 0) {
                throw new RestApiException(AuthErrorCode.FAIL);
            }

            // 사진 삭제
            if (dto.getDelPics() != null) {
                int delPicsAffectedRows = mapper.delMemoryPic(dto);
                if (delPicsAffectedRows == 0) {
                    throw new RestApiException(AuthErrorCode.PICS_FAIL);
                }
            }

            String target = "/memory/" + dto.getImemory();

            MemoryPicsInsDto picsDto = new MemoryPicsInsDto();
            picsDto.setImemory(dto.getImemory());

            if (pics.size() != 0) {
                for (MultipartFile file : pics) {
                    String saveFileNm = myFileUtils.transferTo(file, target);
                    picsDto.getMemoryPics().add(saveFileNm);
                }
                int picsAffectedRows = mapper.insPicsMemory(picsDto);
                if (picsAffectedRows == 0 || pics.size() > 20) {
                    throw new RestApiException(AuthErrorCode.MANY_PIC);
                }
            }
        }
        mapper.delKidRoom(dto.getImemory());

        InsRoomInviteProcDto pdto = InsRoomInviteProcDto.builder()
                .imemory(dto.getImemory())
                .ikids(dto.getIkids())
                .build();

        int invite = mapper.insMemoryRoomInvite(pdto);
        if (invite == Const.ZERO) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }

        for (Integer kid : kids) {
            dto.getIkids().removeAll(Collections.singleton(kid));
        }

        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        String createdAt = nowInKorea.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        if (dto.getIkids().size() != 0) {

            List<SelMemoryOtherTokens> otherTokens = mapper.selTeaFirebaseParents(dto);

            try {
                if (otherTokens != null) {
                    for (SelMemoryOtherTokens token : otherTokens) {
                        MemoryPushVo pushVo = new MemoryPushVo();
                        pushVo.setMemoryTitle(dto.getMemoryTitle());
                        pushVo.setWriterIuser(writerIuser);
                        pushVo.setCreatedAt(createdAt);
                        pushVo.setIkid(token.getIkid());
                        pushVo.setKidNm(token.getKidNm());
                        pushVo.setImemory(dto.getImemory());
                        pushVo.setKidNm(loginUserNm);

                        String body = objMapper.writeValueAsString(pushVo);
                        log.info("body: {}", body);
                        Notification noti = Notification.builder()
                                .setTitle("putMemory")
                                .setBody(body)
                                .build();

                        Message message = Message.builder()
                                .setToken(token.getFirebaseToken())
                                .setNotification(noti)
                                .build();

                        FirebaseMessaging.getInstance().sendAsync(message);
                    }
                }

            } catch (Exception e) {
                throw new RestApiException(AuthErrorCode.PUSH_FAIL);

            }
        }

        return new ResVo(SUCCESS);
    }

}
