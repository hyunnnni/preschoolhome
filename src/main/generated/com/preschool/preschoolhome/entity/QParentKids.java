package com.preschool.preschoolhome.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QParentKids is a Querydsl query type for ParentKids
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QParentKids extends BeanPath<ParentKids> {

    private static final long serialVersionUID = -55318458L;

    public static final QParentKids parentKids = new QParentKids("parentKids");

    public final NumberPath<Integer> ikid = createNumber("ikid", Integer.class);

    public final NumberPath<Integer> iparent = createNumber("iparent", Integer.class);

    public QParentKids(String variable) {
        super(ParentKids.class, forVariable(variable));
    }

    public QParentKids(Path<? extends ParentKids> path) {
        super(path.getType(), path.getMetadata());
    }

    public QParentKids(PathMetadata metadata) {
        super(ParentKids.class, metadata);
    }

}

