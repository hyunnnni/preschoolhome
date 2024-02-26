package com.preschool.preschoolhome.memory;


import com.preschool.preschoolhome.entity.MemoryAlbumEntity;
import com.preschool.preschoolhome.entity.MemoryEntity;
import com.preschool.preschoolhome.memory.model.AllSelMemoryDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.preschool.preschoolhome.entity.QMemoryAlbumEntity.memoryAlbumEntity;
import static com.preschool.preschoolhome.entity.QMemoryEntity.memoryEntity;


@Slf4j
@RequiredArgsConstructor
public class MemoryQdslRepositoryImpl implements MemoryQdslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MemoryEntity> selMemoryAll(AllSelMemoryDto dto, Pageable pageable) {

        JPAQuery<MemoryEntity> jpaQuery = jpaQueryFactory.select(memoryEntity) //.selectfrom(feedEntity)
                .from(memoryEntity)
                .join(memoryEntity.teacherEntity)
                .where(whereTargetUser(dto.getIkid()))
                .fetchJoin() //앨범하나당 유저정보(글쓴이)는 한명이라 페치조인으로 정보 다 들고오기

                .orderBy(memoryEntity.imemory.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return jpaQuery.fetch();

    }

    @Override
    public List<MemoryAlbumEntity> selFeedPicsAll(List<MemoryEntity> feedEntityList) {
        return jpaQueryFactory.select(Projections.fields(MemoryAlbumEntity.class
                , memoryAlbumEntity.memoryEntity
                , memoryAlbumEntity.memoryPic))
                .from(memoryAlbumEntity)
                .where(memoryAlbumEntity.memoryEntity.in(feedEntityList))
                .fetch();
    }


    private BooleanExpression whereTargetUser(int targetIuser){
        return targetIuser ==0 ? null : memoryEntity.teacherEntity.iteacher.eq(targetIuser);
    }
}
