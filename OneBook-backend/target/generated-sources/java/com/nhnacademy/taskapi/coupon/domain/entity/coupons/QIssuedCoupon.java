package com.nhnacademy.taskapi.coupon.domain.entity.coupons;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIssuedCoupon is a Querydsl query type for IssuedCoupon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIssuedCoupon extends EntityPathBase<IssuedCoupon> {

    private static final long serialVersionUID = 195789561L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIssuedCoupon issuedCoupon = new QIssuedCoupon("issuedCoupon");

    public final QCoupon coupon;

    public final DateTimePath<java.time.LocalDateTime> issueDateTime = createDateTime("issueDateTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> issuedCouponId = createNumber("issuedCouponId", Long.class);

    public final com.nhnacademy.taskapi.member.domain.QMember member;

    public final DateTimePath<java.time.LocalDateTime> useDateTime = createDateTime("useDateTime", java.time.LocalDateTime.class);

    public QIssuedCoupon(String variable) {
        this(IssuedCoupon.class, forVariable(variable), INITS);
    }

    public QIssuedCoupon(Path<? extends IssuedCoupon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIssuedCoupon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIssuedCoupon(PathMetadata metadata, PathInits inits) {
        this(IssuedCoupon.class, metadata, inits);
    }

    public QIssuedCoupon(Class<? extends IssuedCoupon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.coupon = inits.isInitialized("coupon") ? new QCoupon(forProperty("coupon"), inits.get("coupon")) : null;
        this.member = inits.isInitialized("member") ? new com.nhnacademy.taskapi.member.domain.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

