package com.preschool.preschoolhome.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemoryCommentEntity is a Querydsl query type for MemoryCommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemoryCommentEntity extends EntityPathBase<MemoryCommentEntity> {

    private static final long serialVersionUID = -2144574862L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemoryCommentEntity memoryCommentEntity = new QMemoryCommentEntity("memoryCommentEntity");

    public final QCreatedAtEntity _super = new QCreatedAtEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> imemoryComment = createNumber("imemoryComment", Integer.class);

    public final StringPath memoryComment = createString("memoryComment");

    public final QMemoryEntity memoryEntity;

    public final QParentEntity parent;

    public final QTeacherEntity teacher;

    public QMemoryCommentEntity(String variable) {
        this(MemoryCommentEntity.class, forVariable(variable), INITS);
    }

    public QMemoryCommentEntity(Path<? extends MemoryCommentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemoryCommentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemoryCommentEntity(PathMetadata metadata, PathInits inits) {
        this(MemoryCommentEntity.class, metadata, inits);
    }

    public QMemoryCommentEntity(Class<? extends MemoryCommentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memoryEntity = inits.isInitialized("memoryEntity") ? new QMemoryEntity(forProperty("memoryEntity"), inits.get("memoryEntity")) : null;
        this.parent = inits.isInitialized("parent") ? new QParentEntity(forProperty("parent"), inits.get("parent")) : null;
        this.teacher = inits.isInitialized("teacher") ? new QTeacherEntity(forProperty("teacher"), inits.get("teacher")) : null;
    }

}

