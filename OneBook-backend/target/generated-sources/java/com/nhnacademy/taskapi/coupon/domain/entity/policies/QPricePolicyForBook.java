package com.nhnacademy.taskapi.coupon.domain.entity.policies;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPricePolicyForBook is a Querydsl query type for PricePolicyForBook
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPricePolicyForBook extends EntityPathBase<PricePolicyForBook> {

    private static final long serialVersionUID = -1643638354L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPricePolicyForBook pricePolicyForBook = new QPricePolicyForBook("pricePolicyForBook");

    public final com.nhnacademy.taskapi.book.domain.QBook book;

    public final StringPath description = createString("description");

    public final NumberPath<Integer> discountPrice = createNumber("discountPrice", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> expirationPeriodEnd = createDateTime("expirationPeriodEnd", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> expirationPeriodStart = createDateTime("expirationPeriodStart", java.time.LocalDateTime.class);

    public final NumberPath<Integer> minimumOrderAmount = createNumber("minimumOrderAmount", Integer.class);

    public final StringPath name = createString("name");

    public final com.nhnacademy.taskapi.coupon.domain.entity.status.QPolicyStatus policyStatus;

    public final NumberPath<Long> pricePolicyForBookId = createNumber("pricePolicyForBookId", Long.class);

    public QPricePolicyForBook(String variable) {
        this(PricePolicyForBook.class, forVariable(variable), INITS);
    }

    public QPricePolicyForBook(Path<? extends PricePolicyForBook> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPricePolicyForBook(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPricePolicyForBook(PathMetadata metadata, PathInits inits) {
        this(PricePolicyForBook.class, metadata, inits);
    }

    public QPricePolicyForBook(Class<? extends PricePolicyForBook> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new com.nhnacademy.taskapi.book.domain.QBook(forProperty("book"), inits.get("book")) : null;
        this.policyStatus = inits.isInitialized("policyStatus") ? new com.nhnacademy.taskapi.coupon.domain.entity.status.QPolicyStatus(forProperty("policyStatus")) : null;
    }

}

