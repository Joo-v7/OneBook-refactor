package com.nhnacademy.taskapi.payment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPayment is a Querydsl query type for Payment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPayment extends EntityPathBase<Payment> {

    private static final long serialVersionUID = -1511215802L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPayment payment = new QPayment("payment");

    public final DateTimePath<java.time.LocalDateTime> approvedAt = createDateTime("approvedAt", java.time.LocalDateTime.class);

    public final StringPath currency = createString("currency");

    public final NumberPath<Integer> onlyBookAmount = createNumber("onlyBookAmount", Integer.class);

    public final com.nhnacademy.taskapi.order.entity.QOrder order;

    public final NumberPath<Long> paymentId = createNumber("paymentId", Long.class);

    public final QPaymentMethod paymentMethod;

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> requestedAt = createDateTime("requestedAt", java.time.LocalDateTime.class);

    public final StringPath status = createString("status");

    public final NumberPath<Integer> totalAmount = createNumber("totalAmount", Integer.class);

    public QPayment(String variable) {
        this(Payment.class, forVariable(variable), INITS);
    }

    public QPayment(Path<? extends Payment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPayment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPayment(PathMetadata metadata, PathInits inits) {
        this(Payment.class, metadata, inits);
    }

    public QPayment(Class<? extends Payment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new com.nhnacademy.taskapi.order.entity.QOrder(forProperty("order"), inits.get("order")) : null;
        this.paymentMethod = inits.isInitialized("paymentMethod") ? new QPaymentMethod(forProperty("paymentMethod")) : null;
    }

}

