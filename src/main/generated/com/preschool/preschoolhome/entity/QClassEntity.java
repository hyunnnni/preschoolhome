package com.preschool.preschoolhome.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClassEntity is a Querydsl query type for ClassEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClassEntity extends EntityPathBase<ClassEntity> {

    private static final long serialVersionUID = 425110348L;

    public static final QClassEntity classEntity = new QClassEntity("classEntity");

    public final QCreatedAtEntity _super = new QCreatedAtEntity(this);

    public final StringPath classNm = createString("classNm");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> iclass = createNumber("iclass", Integer.class);

    public QClassEntity(String variable) {
        super(ClassEntity.class, forVariable(variable));
    }

    public QClassEntity(Path<? extends ClassEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClassEntity(PathMetadata metadata) {
        super(ClassEntity.class, metadata);
    }

}

