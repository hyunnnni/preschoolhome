package com.preschool.preschoolhome.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemoryAlbumEntity is a Querydsl query type for MemoryAlbumEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemoryAlbumEntity extends EntityPathBase<MemoryAlbumEntity> {

    private static final long serialVersionUID = -1752614270L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemoryAlbumEntity memoryAlbumEntity = new QMemoryAlbumEntity("memoryAlbumEntity");

    public final NumberPath<Integer> imemoryPic = createNumber("imemoryPic", Integer.class);

    public final QMemoryEntity memoryEntity;

    public final StringPath memoryPic = createString("memoryPic");

    public QMemoryAlbumEntity(String variable) {
        this(MemoryAlbumEntity.class, forVariable(variable), INITS);
    }

    public QMemoryAlbumEntity(Path<? extends MemoryAlbumEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemoryAlbumEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemoryAlbumEntity(PathMetadata metadata, PathInits inits) {
        this(MemoryAlbumEntity.class, metadata, inits);
    }

    public QMemoryAlbumEntity(Class<? extends MemoryAlbumEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memoryEntity = inits.isInitialized("memoryEntity") ? new QMemoryEntity(forProperty("memoryEntity"), inits.get("memoryEntity")) : null;
    }

}

