package com.preschool.preschoolhome.memory;



import com.preschool.preschoolhome.common.utils.Const;
import com.preschool.preschoolhome.entity.KidEntity;
import com.preschool.preschoolhome.entity.MemoryEntity;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.preschool.preschoolhome.entity.QKidEntity.kidEntity;
import static com.preschool.preschoolhome.entity.QMemoryEntity.memoryEntity;
import static com.preschool.preschoolhome.entity.QMemoryRoomEntity.memoryRoomEntity;
import static com.preschool.preschoolhome.entity.QParentEntity.parentEntity;
import static com.preschool.preschoolhome.entity.QTeacherEntity.teacherEntity;
import static com.preschool.preschoolhome.entity.QParentKidEntity.parentKidEntity;

@Slf4j
@RequiredArgsConstructor
public class KidQdslRepositoryImpl implements KidQdslRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<KidEntity> selKidByIparent(int iparent) {
         return jpaQueryFactory.select(parentKidEntity.kidEntity)
                .from(parentKidEntity)
                .join(parentKidEntity.kidEntity)
                .on(parentKidEntity.parentEntity.iparent.eq(iparent))
                .where(parentKidEntity.parentEntity.iparent.eq(iparent))
                .fetch();
    }
}
