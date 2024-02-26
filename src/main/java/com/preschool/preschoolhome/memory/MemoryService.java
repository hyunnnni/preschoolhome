package com.preschool.preschoolhome.memory;


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
import com.preschool.preschoolhome.entity.KidEntity;
import com.preschool.preschoolhome.entity.MemoryAlbumEntity;
import com.preschool.preschoolhome.entity.MemoryEntity;
import com.preschool.preschoolhome.entity.TeacherEntity;
import com.preschool.preschoolhome.memory.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.K;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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


    /*public AllMemoryVo getAllMemory(AllSelMemoryDto dto, Pageable pageable){
        int level = authenticationFacade.getLevelPk();
        if(level == 2 || level == 3){

        final List<MemoryEntity> list = repository.selMemoryAll(dto, pageable);

        final List<MemoryAlbumEntity> picList = repository.selFeedPicsAll(list);

        final List<MemoryCommentSelVo> cmtList = commentMapper.selFeedCommentEachTop4(list);
        //function 파라미터와 리턴타입이 있음
        //consumer 파라미터만 있음 void
        //Predicate 파라미터 있고 리턴타입이 불린 //조건을 주고 true만 리턴  ex(item -> item %2==0)
        //supplier 파라미터가 없고 리턴타입만 있음
        return list.stream().map(item -> {
                    List<FeedCommentSelVo> eachCommentList = cmtList.stream()
                            .filter(cmt -> cmt.getIfeed() == item.getIfeed())
                            .collect(Collectors.toList());

                    int isMoreComment = 0;
                    if(eachCommentList.size() == 4){
                        isMoreComment = 1;
                        eachCommentList.remove(eachCommentList.size() - 1);
                    }

                    return FeedSelVo.builder()
                            .ifeed(item.getIfeed().intValue())
                            .contents(item.getContents())
                            .location(item.getContents())
                            .createdAt(item.getCreatedAt().toString())
                            .writerIuser(item.getUserEntity().getIuser().intValue())
                            .writerNm(item.getUserEntity().getNm())
                            .writerPic(item.getUserEntity().getPic())
                            .pics(picList.stream()
                                    .filter(pic ->      //filter는 타입은 같음 list 사이즈만 다름
                                            pic.getFeedEntity().getIfeed() == item.getIfeed()
                                    ).map(pic -> pic.getPic()
                                    ).collect(Collectors.toList()) //List
                            )
                            .isFav(dto.getIsFavList() == 1
                                    ? 1
                                    : favList.stream().anyMatch(fav -> fav.getFeedEntity().getIfeed() == item.getIfeed())
                                    ? 1
                                    : 0)
                            .comments(eachCommentList)
                            .isMoreComment(isMoreComment)
                            .build();

                }
        ).collect(Collectors.toList());
    }

        AllMemoryVo vo = new AllMemoryVo();

            List<AllSelMemoryVo> list = repository.selMemoryAll(dto, pageable);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setMemoryComments(mapper.memoryComment(list.get(i).getImemory()));
                list.get(i).setIkids(mapper.iMemoryIkid(list.get(i).getImemory()));
            }
            vo.setList(list);
            vo.setImemoryCnt(mapper.allMemoryTeaCnt(dto));
        }
        //if(roles.get(0).equals("USER") || roles.get(0).equals("GRADUATE")){
        if(level == 1 || level == 4){
            List<AllSelMemoryVo> list = mapper.allMemoryPar(dto);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setMemoryComments(mapper.memoryComment(list.get(i).getImemory()));
                list.get(i).setIkids(mapper.iMemoryIkid(list.get(i).getImemory()));
            }
            vo.setList(list);
            vo.setImemoryCnt(mapper.allMemoryParCnt(dto));
        }
        return vo;
    }*/

    public AllMemoryVo getAllMemory(AllSelMemoryDto dto){
        int level = authenticationFacade.getLevelPk();
        List<String> roles = authenticationFacade.getRoles();
        AllMemoryVo vo = new AllMemoryVo();
        //if(roles.get(0).equals("TEACHER") || roles.get(0).equals("ADMIN")){
        if(level == 2 || level == 3){
            List<AllSelMemoryVo> list = mapper.allMemoryTea(dto);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setMemoryComments(mapper.memoryComment(list.get(i).getImemory()));
                list.get(i).setIkids(mapper.iMemoryIkid(list.get(i).getImemory()));
            }
            vo.setList(list);
            vo.setImemoryCnt(mapper.allMemoryTeaCnt(dto));
        }
        //if(roles.get(0).equals("USER") || roles.get(0).equals("GRADUATE")){
        if(level == 1 || level == 4){
            List<AllSelMemoryVo> list = mapper.allMemoryPar(dto);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setMemoryComments(mapper.memoryComment(list.get(i).getImemory()));
                list.get(i).setIkids(mapper.iMemoryIkid(list.get(i).getImemory()));
            }
            vo.setList(list);
            vo.setImemoryCnt(mapper.allMemoryParCnt(dto));
        }
        return vo;
    }

    public AllSelMemoryVo getMemory(int imemory){
        AllSelMemoryVo vo = mapper.memory(imemory);
        vo.setIkids(mapper.iMemoryIkid(imemory));
        vo.setMemoryComments(mapper.memoryComment(imemory));
        return vo;
    }

    //------------------------------------- 추억 앨범 수정시 원래정보 불러오기 ------------------------------
    public SelMemoryVo getMemoryEdit(int imemory){

        List<String> roles = authenticationFacade.getRoles();

//        if(!(roles.get(0).equals("ADMIN") || roles.get(0).equals("TEACHER"))){
//            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
//        }

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
        memory.setIkid(mapper.selMemoryKid(imemory));



        return memory;
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
    public ResVo delmemory(int imemory) {
        int level = authenticationFacade.getLevelPk();
        if (level < Const.TEACHER) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        int selDel = mapper.selDelAlbum(imemory);
        if (selDel == 0) {
            throw new RestApiException(AuthErrorCode.NO_INFORMATION);
        }

        try {
            MemoryEntity memoryEntity = repository.getReferenceById(imemory);
            repository.delete(memoryEntity);
            return new ResVo(SUCCESS);

        } catch (Exception e) {
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
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
    public ResVo postMemory(List<MultipartFile> pics, InsMemoryDto dto){

        int iuser = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        String loginUserNm = authenticationFacade.getUserNm();

        if(level != Const.TEACHER && level != Const.BOSS){
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
        if(result == Const.ZERO){
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
    }


    //------- 추억앨범 댓글등록 push기능 -----
    @Transactional
    public ResVo postMemoryComment(InsCommentDto dto) {
        if ((dto.getIparent() == 0) && dto.getIteacher() == 0 ||
                (dto.getIteacher() > 0 && dto.getIteacher() > 0)) {
            throw new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION);
        }
        int writerIuser = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        int result = mapper.insComment(dto);
        if (result == 0) {
            return new ResVo(-1);
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createdAt = now.format(formatter);

        String otherTokens = null;
        if (level == Const.PARENT) {
            otherTokens = mapper.selParFirebaseByLoginUserComment(dto.getImemoryComment());
        }
        if (level == Const.TEACHER || level == Const.BOSS) {
            otherTokens = mapper.selTeaFirebaseByLoginUserComment(dto.getImemoryComment());
        }
        try {
            if (otherTokens != null) {
                MemoryCommentPushVo pushVo = new MemoryCommentPushVo();
                pushVo.setMemoryComment(dto.getMemoryComment());
                pushVo.setWriterIuser(writerIuser);
                pushVo.setCreatedAt(createdAt);

                String body = objMapper.writeValueAsString(pushVo);
                log.info("body: {}", body);
                Notification noti = Notification.builder()
                        .setTitle("moemoryComment")
                        .setBody(body)
                        .build();

                Message message = Message.builder()
                        .setToken(otherTokens)
                        .setNotification(noti)
                        .build();

                FirebaseMessaging.getInstance().sendAsync(message);
            }

        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.PUSH_FAIL);
        }
        return new ResVo(result);

    }


    //------------------------------------- 추억 앨범 댓글 삭제 -------------------------------------
    @Transactional
    public ResVo delMemoryComment(DelMemoryCommentDto dto){

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

        }catch (Exception e){
            return new ResVo(Const.FAIL);
        }
    }


    //------------------------------------- 추억 앨범 글 수정 -------------------------------------
    @Transactional
    public ResVo putmemory(List<MultipartFile> pics, MemoryUpdDto dto) {
        int writerIuser = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        String loginUserNm = authenticationFacade.getUserNm();
        if (level < Const.TEACHER) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }

        List<Integer> kids = mapper.selMemoryKid(dto.getImemory());
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
            myFileUtils.delFolderTrigger(target);

            MemoryPicsInsDto picsDto = new MemoryPicsInsDto();
            picsDto.setImemory(dto.getImemory());

            if (pics.size() != 0) {
                for (MultipartFile file : pics) {
                    String saveFileNm = myFileUtils.transferTo(file, target);
                    picsDto.getPics().add(saveFileNm);
                }
                int picsAffectedRows = mapper.insPicsMemory(picsDto);
                if (picsAffectedRows == 0 || pics.size() > 20) {
                    throw new RestApiException(AuthErrorCode.MANY_PIC);
                }
            }
        }

        List<Integer> newKid = new ArrayList<>();
        for (int ikid : dto.getIkids()) {
            newKid.add(ikid);
        }

        newKid.removeAll(kids);

        LocalDateTime now = LocalDateTime.now(); // 현재 날짜 구하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 포맷 정의
        String createdAt = now.format(formatter); // 포맷 적용


        List<SelMemoryOtherTokens> otherTokens = mapper.selTeaFirebaseParents(dto);

        try {
            if (otherTokens != null) {
                for (SelMemoryOtherTokens token : otherTokens) {
                    MemoryPushVo pushVo = new MemoryPushVo();
                    pushVo.setMemoryTitle(dto.getTitle());
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

        return new ResVo(SUCCESS);
    }

}
