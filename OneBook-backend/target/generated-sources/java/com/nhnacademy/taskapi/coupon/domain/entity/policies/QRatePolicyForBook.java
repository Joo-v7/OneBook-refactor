package com.nhnacademy.taskapi.coupon.domain.entity.policies;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRatePolicyForBook is a Querydsl query type for RatePolicyForBook
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRatePolicyForBook extends EntityPathBase<RatePolicyForBook> {

    private static final long serialVersionUID = 1456649001L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRatePolicyForBook ratePolicyForBook = new QRatePolicyForBook("ratePolicyForBook");

    public final com.nhnacademy.taskapi.book.domain.QBook book;

    public final StringPath description = createString("description");

    public final NumberPath<Integer> discountRate = createNumber("discountRate", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> expirationPeriodEnd = createDateTime("expirationPeriodEnd", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> expirationPeriodStart = createDateTime("expirationPeriodStart", java.time.LocalDateTime.class);

    public final NumberPath<Integer> maximumDiscountPrice = createNumber("maximumDiscountPrice", Integer.class);

    public final NumberPath<Integer> minimumOrderAmount = createNumber("minimumOrderAmount", Integer.class);

    public final StringPath name = createString("name");

    public final com.nhnacademy.taskapi.coupon.domain.entity.status.QPolicyStatus policyStatus;

    public final NumberPath<Long> ratePolicyForBookId = createNumber("ratePolicyForBookId", Long.class);

    public QRatePolicyForBook(String variable) {
        this(RatePolicyForBook.class, forVariable(variable), INITS);
    }

    public QRatePolicyForBook(Path<? extends RatePolicyForBook> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRatePolicyForBook(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRatePolicyForBook(PathMetadata metadata, PathInits inits) {
        this(RatePolicyForBook.class, metadata, inits);
    }

    public QRatePolicyForBook(Class<? extends RatePolicyForBook> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new com.nhnacademy.taskapi.book.domain.QBook(forProperty("book"), inits.get("book")) : null;
        this.policyStatus = inits.isInitialized("policyStatus") ? new com.nhnacademy.taskapi.coupon.domain.entity.status.QPolicyStatus(forProperty("policyStatus")) : null;
    }

}

