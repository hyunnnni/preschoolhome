package com.preschool.preschoolhome.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QKidEntity is a Querydsl query type for KidEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QKidEntity extends EntityPathBase<KidEntity> {

    private static final long serialVersionUID = 77049434L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QKidEntity kidEntity = new QKidEntity("kidEntity");

    public final QCreatedAtEntity _super = new QCreatedAtEntity(this);

    public final StringPath address = createString("address");

    public final DatePath<java.util.Date> birth = createDate("birth", java.util.Date.class);

    public final QClassEntity classEntity;

    public final StringPath code = createString("code");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath emerNb = createString("emerNb");

    public final StringPath emerNm = createString("emerNm");

    public final NumberPath<Integer> gender = createNumber("gender", Integer.class);

    public final NumberPath<Integer> ikid = createNumber("ikid", Integer.class);

    public final StringPath kidNm = createString("kidNm");

    public final StringPath memo = createString("memo");

    public final StringPath profile = createString("profile");

    public final NumberPath<Integer> state = createNumber("state", Integer.class);

    public QKidEntity(String variable) {
        this(KidEntity.class, forVariable(variable), INITS);
    }

    public QKidEntity(Path<? extends KidEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QKidEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QKidEntity(PathMetadata metadata, PathInits inits) {
        this(KidEntity.class, metadata, inits);
    }

    public QKidEntity(Class<? extends KidEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.classEntity = inits.isInitialized("classEntity") ? new QClassEntity(forProperty("classEntity")) : null;
    }

}

