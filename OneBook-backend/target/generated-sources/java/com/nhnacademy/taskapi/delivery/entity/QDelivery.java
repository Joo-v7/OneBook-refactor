package com.nhnacademy.taskapi.delivery.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDelivery is a Querydsl query type for Delivery
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDelivery extends EntityPathBase<Delivery> {

    private static final long serialVersionUID = 1651513575L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDelivery delivery = new QDelivery("delivery");

    public final NumberPath<Long> deliveryId = createNumber("deliveryId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> doneDate = createDateTime("doneDate", java.time.LocalDateTime.class);

    public final DatePath<java.time.LocalDate> expectedDate = createDate("expectedDate", java.time.LocalDate.class);

    public final StringPath invoiceNumber = createString("invoiceNumber");

    public final com.nhnacademy.taskapi.order.entity.QOrder order;

    public final StringPath recipientAddress = createString("recipientAddress");

    public final StringPath recipientName = createString("recipientName");

    public final StringPath recipientPhoneNumber = createString("recipientPhoneNumber");

    public final StringPath recipientRequestedTerm = createString("recipientRequestedTerm");

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final EnumPath<com.nhnacademy.taskapi.delivery.domain.Status> status = createEnum("status", com.nhnacademy.taskapi.delivery.domain.Status.class);

    public QDelivery(String variable) {
        this(Delivery.class, forVariable(variable), INITS);
    }

    public QDelivery(Path<? extends Delivery> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDelivery(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDelivery(PathMetadata metadata, PathInits inits) {
        this(Delivery.class, metadata, inits);
    }

    public QDelivery(Class<? extends Delivery> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new com.nhnacademy.taskapi.order.entity.QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

