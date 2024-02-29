package com.preschool.preschoolhome.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRelationEntity is a Querydsl query type for RelationEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRelationEntity extends EntityPathBase<RelationEntity> {

    private static final long serialVersionUID = -94350450L;

    public static final QRelationEntity relationEntity = new QRelationEntity("relationEntity");

    public final QCreatedAtEntity _super = new QCreatedAtEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> irelation = createNumber("irelation", Integer.class);

    public final StringPath relation = createString("relation");

    public QRelationEntity(String variable) {
        super(RelationEntity.class, forVariable(variable));
    }

    public QRelationEntity(Path<? extends RelationEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRelationEntity(PathMetadata metadata) {
        super(RelationEntity.class, metadata);
    }

}

