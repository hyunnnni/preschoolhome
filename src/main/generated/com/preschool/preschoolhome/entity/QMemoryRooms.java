package com.preschool.preschoolhome.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemoryRooms is a Querydsl query type for MemoryRooms
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMemoryRooms extends BeanPath<MemoryRooms> {

    private static final long serialVersionUID = -1304620472L;

    public static final QMemoryRooms memoryRooms = new QMemoryRooms("memoryRooms");

    public final NumberPath<Integer> ikid = createNumber("ikid", Integer.class);

    public final NumberPath<Integer> imemory = createNumber("imemory", Integer.class);

    public QMemoryRooms(String variable) {
        super(MemoryRooms.class, forVariable(variable));
    }

    public QMemoryRooms(Path<? extends MemoryRooms> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemoryRooms(PathMetadata metadata) {
        super(MemoryRooms.class, metadata);
    }

}

