package com.preschool.preschoolhome.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeacherEntity is a Querydsl query type for TeacherEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeacherEntity extends EntityPathBase<TeacherEntity> {

    private static final long serialVersionUID = -1686493706L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTeacherEntity teacherEntity = new QTeacherEntity("teacherEntity");

    public final QCreatedAtEntity _super = new QCreatedAtEntity(this);

    public final QClassEntity classEntity;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> iteacher = createNumber("iteacher", Integer.class);

    public final StringPath tcEmail = createString("tcEmail");

    public final StringPath tcFirebaseToken = createString("tcFirebaseToken");

    public final NumberPath<Integer> tcIsDel = createNumber("tcIsDel", Integer.class);

    public final StringPath tcRole = createString("tcRole");

    public final StringPath teacherIntroduce = createString("teacherIntroduce");

    public final StringPath teacherNm = createString("teacherNm");

    public final StringPath teacherProfile = createString("teacherProfile");

    public final StringPath teacherUid = createString("teacherUid");

    public final StringPath teacherUpw = createString("teacherUpw");

    public QTeacherEntity(String variable) {
        this(TeacherEntity.class, forVariable(variable), INITS);
    }

    public QTeacherEntity(Path<? extends TeacherEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTeacherEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTeacherEntity(PathMetadata metadata, PathInits inits) {
        this(TeacherEntity.class, metadata, inits);
    }

    public QTeacherEntity(Class<? extends TeacherEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.classEntity = inits.isInitialized("classEntity") ? new QClassEntity(forProperty("classEntity")) : null;
    }

}

