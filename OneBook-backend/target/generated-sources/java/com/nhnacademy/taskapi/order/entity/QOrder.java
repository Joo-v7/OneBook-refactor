package com.nhnacademy.taskapi.order.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 1383165605L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final DateTimePath<java.time.LocalDateTime> dateTime = createDateTime("dateTime", java.time.LocalDateTime.class);

    public final ListPath<com.nhnacademy.taskapi.delivery.entity.Delivery, com.nhnacademy.taskapi.delivery.entity.QDelivery> deliveryList = this.<com.nhnacademy.taskapi.delivery.entity.Delivery, com.nhnacademy.taskapi.delivery.entity.QDelivery>createList("deliveryList", com.nhnacademy.taskapi.delivery.entity.Delivery.class, com.nhnacademy.taskapi.delivery.entity.QDelivery.class, PathInits.DIRECT2);

    public final NumberPath<Integer> deliveryPrice = createNumber("deliveryPrice", Integer.class);

    public final com.nhnacademy.taskapi.member.domain.QMember member;

    public final ListPath<OrderDetail, QOrderDetail> orderDetailList = this.<OrderDetail, QOrderDetail>createList("orderDetailList", OrderDetail.class, QOrderDetail.class, PathInits.DIRECT2);

    public final StringPath ordererName = createString("ordererName");

    public final StringPath ordererPhoneNumber = createString("ordererPhoneNumber");

    public final NumberPath<Long> orderId = createNumber("orderId", Long.class);

    public final StringPath orderName = createString("orderName");

    public final QOrderStatus orderStatus;

    public final com.nhnacademy.taskapi.packaging.entity.QPackaging packaging;

    public final NumberPath<Integer> packagingPrice = createNumber("packagingPrice", Integer.class);

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.nhnacademy.taskapi.member.domain.QMember(forProperty("member"), inits.get("member")) : null;
        this.orderStatus = inits.isInitialized("orderStatus") ? new QOrderStatus(forProperty("orderStatus")) : null;
        this.packaging = inits.isInitialized("packaging") ? new com.nhnacademy.taskapi.packaging.entity.QPackaging(forProperty("packaging")) : null;
    }

}

