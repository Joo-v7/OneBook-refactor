package com.nhnacademy.taskapi.point.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPointPolicy is a Querydsl query type for PointPolicy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointPolicy extends EntityPathBase<PointPolicy> {

    private static final long serialVersionUID = -650665256L;

    public static final QPointPolicy pointPolicy = new QPointPolicy("pointPolicy");

    public final NumberPath<Integer> pointPolicyApplyAmount = createNumber("pointPolicyApplyAmount", Integer.class);

    public final BooleanPath pointPolicyApplyType = createBoolean("pointPolicyApplyType");

    public final StringPath pointPolicyCondition = createString("pointPolicyCondition");

    public final NumberPath<Integer> pointPolicyConditionAmount = createNumber("pointPolicyConditionAmount", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> pointPolicyCreatedAt = createDateTime("pointPolicyCreatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> pointPolicyId = createNumber("pointPolicyId", Long.class);

    public final StringPath pointPolicyName = createString("pointPolicyName");

    public final NumberPath<Integer> pointPolicyRate = createNumber("pointPolicyRate", Integer.class);

    public final BooleanPath pointPolicyState = createBoolean("pointPolicyState");

    public final DateTimePath<java.time.LocalDateTime> pointPolicyUpdatedAt = createDateTime("pointPolicyUpdatedAt", java.time.LocalDateTime.class);

    public QPointPolicy(String variable) {
        super(PointPolicy.class, forVariable(variable));
    }

    public QPointPolicy(Path<? extends PointPolicy> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointPolicy(PathMetadata metadata) {
        super(PointPolicy.class, metadata);
    }

}

