package com.preschool.preschoolhome.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QParentEntity is a Querydsl query type for ParentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QParentEntity extends EntityPathBase<ParentEntity> {

    private static final long serialVersionUID = -1788117124L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QParentEntity parentEntity = new QParentEntity("parentEntity");

    public final QCreatedAtEntity _super = new QCreatedAtEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> duplicationCheck = createNumber("duplicationCheck", Integer.class);

    public final NumberPath<Integer> iparent = createNumber("iparent", Integer.class);

    public final StringPath parentNm = createString("parentNm");

    public final StringPath phoneNb = createString("phoneNb");

    public final StringPath prEmail = createString("prEmail");

    public final StringPath prFirebaseToken = createString("prFirebaseToken");

    public final NumberPath<Integer> prIsDel = createNumber("prIsDel", Integer.class);

    public final StringPath prRole = createString("prRole");

    public final QRelationEntity relationEntity;

    public final StringPath uid = createString("uid");

    public final StringPath upw = createString("upw");

    public QParentEntity(String variable) {
        this(ParentEntity.class, forVariable(variable), INITS);
    }

    public QParentEntity(Path<? extends ParentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QParentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QParentEntity(PathMetadata metadata, PathInits inits) {
        this(ParentEntity.class, metadata, inits);
    }

    public QParentEntity(Class<? extends ParentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.relationEntity = inits.isInitialized("relationEntity") ? new QRelationEntity(forProperty("relationEntity")) : null;
    }

}

