package com.preschool.preschoolhome.memory;


import com.preschool.preschoolhome.common.utils.Const;
import com.preschool.preschoolhome.common.utils.ResVo;
import com.preschool.preschoolhome.entity.CreatedAtEntity;
import com.preschool.preschoolhome.entity.MemoryAlbumEntity;
import com.preschool.preschoolhome.entity.MemoryCommentEntity;
import com.preschool.preschoolhome.entity.MemoryEntity;
import com.preschool.preschoolhome.memory.model.AllSelMemoryDto;
import com.preschool.preschoolhome.memory.model.DelMemoryCommentDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;

import java.time.Year;
import java.util.List;

import static com.preschool.preschoolhome.entity.QCreatedAtEntity.createdAtEntity;
import static com.preschool.preschoolhome.entity.QMemoryAlbumEntity.memoryAlbumEntity;
import static com.preschool.preschoolhome.entity.QMemoryCommentEntity.memoryCommentEntity;
import static com.preschool.preschoolhome.entity.QMemoryEntity.memoryEntity;
import static com.preschool.preschoolhome.entity.QMemoryRoomEntity.memoryRoomEntity;
import static com.preschool.preschoolhome.entity.QParentEntity.parentEntity;
import static com.preschool.preschoolhome.entity.QTeacherEntity.teacherEntity;
import static com.preschool.preschoolhome.entity.QKidEntity.kidEntity;


@Slf4j
@RequiredArgsConstructor
public class MemoryQdslRepositoryImpl implements MemoryQdslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MemoryEntity> selMemoryAll(AllSelMemoryDto dto) {

        JPAQuery<MemoryEntity> jpaQuery = jpaQueryFactory.selectDistinct(memoryEntity)
                .from(memoryRoomEntity)
                .rightJoin(memoryRoomEntity.memoryEntity)
                .on(memoryRoomEntity.memoryEntity.imemory.eq(memoryEntity.imemory))
                .leftJoin(kidEntity)
                .on(kidEntity.ikid.eq(memoryRoomEntity.memoryRooms.ikid))
                .leftJoin(teacherEntity)
                .on(teacherEntity.iteacher.eq(memoryEntity.teacherEntity.iteacher))
                .where(whereClausSelMemoryAll(dto.getYear(),dto.getIclass(), dto.getIkid(), dto.getSearch()))
                .offset(dto.getStartIdx())
                .limit(dto.getRowCount())
                .orderBy(memoryEntity.imemory.desc());

// select * from memory; // 1, 5, 7
// select * from memory_room where imemory in (1, 5, 7)
        return jpaQuery.fetch();

    }

    @Override
    public Long selMemoryAllCnt(AllSelMemoryDto dto) {
        return jpaQueryFactory.select(memoryEntity.imemory.countDistinct())
                .from(memoryRoomEntity)
                .join(memoryRoomEntity.memoryEntity)
                .on(memoryRoomEntity.memoryEntity.imemory.eq(memoryEntity.imemory))
                .join(kidEntity)
                .on(kidEntity.ikid.eq(memoryRoomEntity.memoryRooms.ikid))
                .join(teacherEntity)
                .on(teacherEntity.iteacher.eq(memoryEntity.teacherEntity.iteacher))
                .where(whereClausSelMemoryAll(dto.getYear(),dto.getIclass(), dto.getIkid(), dto.getSearch()))
                .fetchOne();
    }

    @Override
    public MemoryEntity findAllByKidPksAndImemory(List<Integer> kidPks, int imemory) {

        return jpaQueryFactory.select(memoryRoomEntity.memoryEntity)
                .from(memoryRoomEntity)
                .join(memoryRoomEntity.memoryEntity)
                .on(memoryRoomEntity.memoryEntity.imemory.eq(imemory))  // imemory 조건을 where 절로 이동
                .join(kidEntity)
                .on(kidEntity.ikid.eq(memoryRoomEntity.memoryRooms.ikid))
                .join(memoryEntity.teacherEntity)
                .on(teacherEntity.iteacher.eq(memoryEntity.teacherEntity.iteacher))
                .where(kidEntity.ikid.in(kidPks))
                .orderBy(memoryEntity.imemory.desc())
                .fetchOne();
    }

    private BooleanBuilder whereClausSelMemoryAll(int year,int iclass, int ikid, String search) {
        BooleanBuilder builder = new BooleanBuilder();
        if(year > 0){
//            builder.and(memoryEntity._super.createdAt.year().eq(year));
            builder.and(memoryEntity.createdAt.year().eq(year));
        }

        if(iclass > 0){
            //jpaQuery.where(kidEntity.classEntity.iclass.eq(dto.getIclass()));
            builder.and(kidEntity.classEntity.iclass.eq(iclass));
        }
        if(ikid > 0){
            //jpaQuery.where(kidEntity.ikid.eq(dto.getIkid()));
            builder.and(kidEntity.ikid.eq(ikid));
        }
        if(search != null){
            //jpaQuery.where(memoryEntity.title.like("%" + dto.getSearch() + "%")
                    //.or(kidEntity.kidNm.like("%" + dto.getSearch() + "%")));
            builder.and((memoryEntity.title.like("%" + search + "%")
                    .or(kidEntity.kidNm.like("%" + search + "%"))));
        }
        return builder;

    }

    @Override
    public List<MemoryAlbumEntity> selMemoryPicsAll(List<MemoryEntity> memoryEntityList) {
        return jpaQueryFactory.select(Projections.fields(MemoryAlbumEntity.class
                , memoryAlbumEntity.memoryEntity
                , memoryAlbumEntity.memoryPic))
                .from(memoryAlbumEntity)
                .where(memoryAlbumEntity.memoryEntity.in(memoryEntityList))
                .fetch();
    }

    @Override
    public List<MemoryCommentEntity> selMemoryCommentAll(List<MemoryEntity> memoryEntityList) {
        return jpaQueryFactory.select(Projections.fields(MemoryCommentEntity.class
            , memoryCommentEntity.memoryEntity
            , memoryCommentEntity.imemoryComment
            , memoryCommentEntity.memoryComment
            , memoryCommentEntity.createdAt))
                .from(memoryCommentEntity)
                .where(memoryCommentEntity.memoryEntity.in(memoryEntityList))
                .fetch();
    }


    private BooleanExpression whereTargetUser(int targetIuser){
        return targetIuser == 0 ? null : memoryEntity.teacherEntity.iteacher.eq(targetIuser);
    }

    @Override
    public ResVo delMemoryComment(DelMemoryCommentDto dto) {
        long executedNum = jpaQueryFactory.delete(memoryCommentEntity)
                .where(memoryCommentEntity.imemoryComment.eq(dto.getImemoryComment()), whereTargetUserComDel(dto))
                .execute();
        log.info("executedNum : {}" ,executedNum);
        return new ResVo((int)executedNum);
    }

    @Override
    public List<MemoryCommentEntity> findAllByMemoryEntity(MemoryEntity memoryEntity) {

        return jpaQueryFactory.selectFrom(memoryCommentEntity)
                .leftJoin(parentEntity)
                .on(memoryCommentEntity.parent.iparent.eq(parentEntity.iparent))
                .leftJoin(teacherEntity)
                .on(memoryCommentEntity.teacher.iteacher.eq(teacherEntity.iteacher))
                .where(memoryCommentEntity.memoryEntity.imemory.eq(memoryEntity.getImemory()))
                .fetch();

    }

    private BooleanExpression whereTargetUserComDel(DelMemoryCommentDto dto) {
        return dto.getIparent() > 0
                ? memoryCommentEntity.parent.iparent.eq(dto.getIparent())
                : memoryCommentEntity.teacher.iteacher.eq(dto.getIteacher());
    }

}
