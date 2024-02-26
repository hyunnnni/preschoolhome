package com.preschool.preschoolhome.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemoryRoomEntity is a Querydsl query type for MemoryRoomEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemoryRoomEntity extends EntityPathBase<MemoryRoomEntity> {

    private static final long serialVersionUID = 364944206L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemoryRoomEntity memoryRoomEntity = new QMemoryRoomEntity("memoryRoomEntity");

    public final QKidEntity kidEntity;

    public final QMemoryEntity memoryEntity;

    public final QMemoryRooms memoryRooms;

    public QMemoryRoomEntity(String variable) {
        this(MemoryRoomEntity.class, forVariable(variable), INITS);
    }

    public QMemoryRoomEntity(Path<? extends MemoryRoomEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemoryRoomEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemoryRoomEntity(PathMetadata metadata, PathInits inits) {
        this(MemoryRoomEntity.class, metadata, inits);
    }

    public QMemoryRoomEntity(Class<? extends MemoryRoomEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.kidEntity = inits.isInitialized("kidEntity") ? new QKidEntity(forProperty("kidEntity"), inits.get("kidEntity")) : null;
        this.memoryEntity = inits.isInitialized("memoryEntity") ? new QMemoryEntity(forProperty("memoryEntity"), inits.get("memoryEntity")) : null;
        this.memoryRooms = inits.isInitialized("memoryRooms") ? new QMemoryRooms(forProperty("memoryRooms")) : null;
    }

}

