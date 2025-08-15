package com.nhnacademy.taskapi.coupon.domain.entity.status;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCouponStatus is a Querydsl query type for CouponStatus
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCouponStatus extends EntityPathBase<CouponStatus> {

    private static final long serialVersionUID = 1884571277L;

    public static final QCouponStatus couponStatus = new QCouponStatus("couponStatus");

    public final NumberPath<Integer> couponStatusId = createNumber("couponStatusId", Integer.class);

    public final StringPath name = createString("name");

    public QCouponStatus(String variable) {
        super(CouponStatus.class, forVariable(variable));
    }

    public QCouponStatus(Path<? extends CouponStatus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCouponStatus(PathMetadata metadata) {
        super(CouponStatus.class, metadata);
    }

}

