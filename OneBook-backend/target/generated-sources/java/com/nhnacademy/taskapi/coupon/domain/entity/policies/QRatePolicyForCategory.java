package com.nhnacademy.taskapi.coupon.domain.entity.policies;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRatePolicyForCategory is a Querydsl query type for RatePolicyForCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRatePolicyForCategory extends EntityPathBase<RatePolicyForCategory> {

    private static final long serialVersionUID = 103879998L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRatePolicyForCategory ratePolicyForCategory = new QRatePolicyForCategory("ratePolicyForCategory");

    public final com.nhnacademy.taskapi.category.domain.QCategory category;

    public final StringPath description = createString("description");

    public final NumberPath<Integer> discountRate = createNumber("discountRate", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> expirationPeriodEnd = createDateTime("expirationPeriodEnd", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> expirationPeriodStart = createDateTime("expirationPeriodStart", java.time.LocalDateTime.class);

    public final NumberPath<Integer> maximumDiscountPrice = createNumber("maximumDiscountPrice", Integer.class);

    public final NumberPath<Integer> minimumOrderAmount = createNumber("minimumOrderAmount", Integer.class);

    public final StringPath name = createString("name");

    public final com.nhnacademy.taskapi.coupon.domain.entity.status.QPolicyStatus policyStatus;

    public final NumberPath<Long> ratePolicyForCategoryId = createNumber("ratePolicyForCategoryId", Long.class);

    public QRatePolicyForCategory(String variable) {
        this(RatePolicyForCategory.class, forVariable(variable), INITS);
    }

    public QRatePolicyForCategory(Path<? extends RatePolicyForCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRatePolicyForCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRatePolicyForCategory(PathMetadata metadata, PathInits inits) {
        this(RatePolicyForCategory.class, metadata, inits);
    }

    public QRatePolicyForCategory(Class<? extends RatePolicyForCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.nhnacademy.taskapi.category.domain.QCategory(forProperty("category"), inits.get("category")) : null;
        this.policyStatus = inits.isInitialized("policyStatus") ? new com.nhnacademy.taskapi.coupon.domain.entity.status.QPolicyStatus(forProperty("policyStatus")) : null;
    }

}

