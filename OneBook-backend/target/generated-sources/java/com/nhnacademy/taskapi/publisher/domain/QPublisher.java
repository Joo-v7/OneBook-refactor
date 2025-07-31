package com.nhnacademy.taskapi.publisher.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPublisher is a Querydsl query type for Publisher
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPublisher extends EntityPathBase<Publisher> {

    private static final long serialVersionUID = -2090317946L;

    public static final QPublisher publisher = new QPublisher("publisher");

    public final StringPath name = createString("name");

    public final NumberPath<Long> publisherId = createNumber("publisherId", Long.class);

    public QPublisher(String variable) {
        super(Publisher.class, forVariable(variable));
    }

    public QPublisher(Path<? extends Publisher> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPublisher(PathMetadata metadata) {
        super(Publisher.class, metadata);
    }

}

