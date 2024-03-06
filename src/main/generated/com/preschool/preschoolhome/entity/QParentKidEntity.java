package com.preschool.preschoolhome.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QParentKidEntity is a Querydsl query type for ParentKidEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QParentKidEntity extends EntityPathBase<ParentKidEntity> {

    private static final long serialVersionUID = 246797328L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QParentKidEntity parentKidEntity = new QParentKidEntity("parentKidEntity");

    public final QKidEntity kidEntity;

    public final QParentEntity parentEntity;

    public final QParentKids parentKids;

    public QParentKidEntity(String variable) {
        this(ParentKidEntity.class, forVariable(variable), INITS);
    }

    public QParentKidEntity(Path<? extends ParentKidEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QParentKidEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QParentKidEntity(PathMetadata metadata, PathInits inits) {
        this(ParentKidEntity.class, metadata, inits);
    }

    public QParentKidEntity(Class<? extends ParentKidEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.kidEntity = inits.isInitialized("kidEntity") ? new QKidEntity(forProperty("kidEntity"), inits.get("kidEntity")) : null;
        this.parentEntity = inits.isInitialized("parentEntity") ? new QParentEntity(forProperty("parentEntity"), inits.get("parentEntity")) : null;
        this.parentKids = inits.isInitialized("parentKids") ? new QParentKids(forProperty("parentKids")) : null;
    }

}

