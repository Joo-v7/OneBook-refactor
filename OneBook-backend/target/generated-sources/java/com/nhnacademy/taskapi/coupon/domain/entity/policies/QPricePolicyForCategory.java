package com.nhnacademy.taskapi.coupon.domain.entity.policies;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPricePolicyForCategory is a Querydsl query type for PricePolicyForCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPricePolicyForCategory extends EntityPathBase<PricePolicyForCategory> {

    private static final long serialVersionUID = -556160701L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPricePolicyForCategory pricePolicyForCategory = new QPricePolicyForCategory("pricePolicyForCategory");

    public final com.nhnacademy.taskapi.category.domain.QCategory category;

    public final StringPath description = createString("description");

    public final NumberPath<Integer> discountPrice = createNumber("discountPrice", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> expirationPeriodEnd = createDateTime("expirationPeriodEnd", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> expirationPeriodStart = createDateTime("expirationPeriodStart", java.time.LocalDateTime.class);

    public final NumberPath<Integer> minimumOrderAmount = createNumber("minimumOrderAmount", Integer.class);

    public final StringPath name = createString("name");

    public final com.nhnacademy.taskapi.coupon.domain.entity.status.QPolicyStatus policyStatus;

    public final NumberPath<Long> pricePolicyForCategoryId = createNumber("pricePolicyForCategoryId", Long.class);

    public QPricePolicyForCategory(String variable) {
        this(PricePolicyForCategory.class, forVariable(variable), INITS);
    }

    public QPricePolicyForCategory(Path<? extends PricePolicyForCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPricePolicyForCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPricePolicyForCategory(PathMetadata metadata, PathInits inits) {
        this(PricePolicyForCategory.class, metadata, inits);
    }

    public QPricePolicyForCategory(Class<? extends PricePolicyForCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.nhnacademy.taskapi.category.domain.QCategory(forProperty("category"), inits.get("category")) : null;
        this.policyStatus = inits.isInitialized("policyStatus") ? new com.nhnacademy.taskapi.coupon.domain.entity.status.QPolicyStatus(forProperty("policyStatus")) : null;
    }

}

