package com.nhnacademy.taskapi.coupon.domain.entity.coupons;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCoupon is a Querydsl query type for Coupon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCoupon extends EntityPathBase<Coupon> {

    private static final long serialVersionUID = -1960515986L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCoupon coupon = new QCoupon("coupon");

    public final NumberPath<Long> couponId = createNumber("couponId", Long.class);

    public final StringPath couponNumber = createString("couponNumber");

    public final com.nhnacademy.taskapi.coupon.domain.entity.status.QCouponStatus couponStatus;

    public final DateTimePath<java.time.LocalDateTime> creationTime = createDateTime("creationTime", java.time.LocalDateTime.class);

    public final QIssuedCoupon issuedCoupon;

    public final com.nhnacademy.taskapi.coupon.domain.entity.policies.QPricePolicyForBook pricePolicyForBook;

    public final com.nhnacademy.taskapi.coupon.domain.entity.policies.QPricePolicyForCategory pricePolicyForCategory;

    public final com.nhnacademy.taskapi.coupon.domain.entity.policies.QRatePolicyForBook ratePolicyForBook;

    public final com.nhnacademy.taskapi.coupon.domain.entity.policies.QRatePolicyForCategory ratePolicyForCategory;

    public QCoupon(String variable) {
        this(Coupon.class, forVariable(variable), INITS);
    }

    public QCoupon(Path<? extends Coupon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCoupon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCoupon(PathMetadata metadata, PathInits inits) {
        this(Coupon.class, metadata, inits);
    }

    public QCoupon(Class<? extends Coupon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.couponStatus = inits.isInitialized("couponStatus") ? new com.nhnacademy.taskapi.coupon.domain.entity.status.QCouponStatus(forProperty("couponStatus")) : null;
        this.issuedCoupon = inits.isInitialized("issuedCoupon") ? new QIssuedCoupon(forProperty("issuedCoupon"), inits.get("issuedCoupon")) : null;
        this.pricePolicyForBook = inits.isInitialized("pricePolicyForBook") ? new com.nhnacademy.taskapi.coupon.domain.entity.policies.QPricePolicyForBook(forProperty("pricePolicyForBook"), inits.get("pricePolicyForBook")) : null;
        this.pricePolicyForCategory = inits.isInitialized("pricePolicyForCategory") ? new com.nhnacademy.taskapi.coupon.domain.entity.policies.QPricePolicyForCategory(forProperty("pricePolicyForCategory"), inits.get("pricePolicyForCategory")) : null;
        this.ratePolicyForBook = inits.isInitialized("ratePolicyForBook") ? new com.nhnacademy.taskapi.coupon.domain.entity.policies.QRatePolicyForBook(forProperty("ratePolicyForBook"), inits.get("ratePolicyForBook")) : null;
        this.ratePolicyForCategory = inits.isInitialized("ratePolicyForCategory") ? new com.nhnacademy.taskapi.coupon.domain.entity.policies.QRatePolicyForCategory(forProperty("ratePolicyForCategory"), inits.get("ratePolicyForCategory")) : null;
    }

}

