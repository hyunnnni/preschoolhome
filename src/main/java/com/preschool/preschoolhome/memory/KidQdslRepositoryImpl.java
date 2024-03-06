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


@Slf4j
@RequiredArgsConstructor
public class KidQdslRepositoryImpl implements KidQdslRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<KidEntity> selKidByIparent(int iparent) {

        /*return jpaQueryFactory.select(parentkidEntity.kidEntity)
                .from(parentkidEntity)
                .join(parentkidEntity.kidEntity)
                .on(parentkidEntity.kidEntity.iparent.eq(parentEntity.iparent))
                .where(dto.getYear(),dto.getIclass(), dto.getIkid(), dto.getSearch());
        */return null;
    }
}
