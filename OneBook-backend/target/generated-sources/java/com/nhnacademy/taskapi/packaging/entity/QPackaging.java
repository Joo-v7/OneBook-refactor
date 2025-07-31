package com.nhnacademy.taskapi.packaging.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPackaging is a Querydsl query type for Packaging
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPackaging extends EntityPathBase<Packaging> {

    private static final long serialVersionUID = -1091822907L;

    public static final QPackaging packaging = new QPackaging("packaging");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public QPackaging(String variable) {
        super(Packaging.class, forVariable(variable));
    }

    public QPackaging(Path<? extends Packaging> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPackaging(PathMetadata metadata) {
        super(Packaging.class, metadata);
    }

}

