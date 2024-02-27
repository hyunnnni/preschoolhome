package com.preschool.preschoolhome.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemoryEntity is a Querydsl query type for MemoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemoryEntity extends EntityPathBase<MemoryEntity> {

    private static final long serialVersionUID = 2133481107L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemoryEntity memoryEntity = new QMemoryEntity("memoryEntity");

    public final StringPath contents = createString("contents");

    public final NumberPath<Integer> imemory = createNumber("imemory", Integer.class);

    public final ListPath<MemoryAlbumEntity, QMemoryAlbumEntity> memoryAlbumEntityList = this.<MemoryAlbumEntity, QMemoryAlbumEntity>createList("memoryAlbumEntityList", MemoryAlbumEntity.class, QMemoryAlbumEntity.class, PathInits.DIRECT2);

    public final ListPath<MemoryCommentEntity, QMemoryCommentEntity> memoryCommentEntityList = this.<MemoryCommentEntity, QMemoryCommentEntity>createList("memoryCommentEntityList", MemoryCommentEntity.class, QMemoryCommentEntity.class, PathInits.DIRECT2);

    public final ListPath<MemoryRoomEntity, QMemoryRoomEntity> memoryRomeEntityList = this.<MemoryRoomEntity, QMemoryRoomEntity>createList("memoryRomeEntityList", MemoryRoomEntity.class, QMemoryRoomEntity.class, PathInits.DIRECT2);

    public final QTeacherEntity teacherEntity;

    public final StringPath title = createString("title");

    public QMemoryEntity(String variable) {
        this(MemoryEntity.class, forVariable(variable), INITS);
    }

    public QMemoryEntity(Path<? extends MemoryEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemoryEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemoryEntity(PathMetadata metadata, PathInits inits) {
        this(MemoryEntity.class, metadata, inits);
    }

    public QMemoryEntity(Class<? extends MemoryEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.teacherEntity = inits.isInitialized("teacherEntity") ? new QTeacherEntity(forProperty("teacherEntity"), inits.get("teacherEntity")) : null;
    }

}

