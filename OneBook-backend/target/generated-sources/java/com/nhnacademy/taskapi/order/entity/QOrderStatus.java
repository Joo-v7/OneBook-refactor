package com.nhnacademy.taskapi.order.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderStatus is a Querydsl query type for OrderStatus
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderStatus extends EntityPathBase<OrderStatus> {

    private static final long serialVersionUID = -1816274185L;

    public static final QOrderStatus orderStatus = new QOrderStatus("orderStatus");

    public final NumberPath<Integer> orderStatusId = createNumber("orderStatusId", Integer.class);

    public final StringPath statusName = createString("statusName");

    public QOrderStatus(String variable) {
        super(OrderStatus.class, forVariable(variable));
    }

    public QOrderStatus(Path<? extends OrderStatus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderStatus(PathMetadata metadata) {
        super(OrderStatus.class, metadata);
    }

}

