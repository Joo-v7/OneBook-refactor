package com.nhnacademy.taskapi.coupon.domain.entity.status;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPolicyStatus is a Querydsl query type for PolicyStatus
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPolicyStatus extends EntityPathBase<PolicyStatus> {

    private static final long serialVersionUID = -1757668711L;

    public static final QPolicyStatus policyStatus = new QPolicyStatus("policyStatus");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> policyStatusId = createNumber("policyStatusId", Integer.class);

    public QPolicyStatus(String variable) {
        super(PolicyStatus.class, forVariable(variable));
    }

    public QPolicyStatus(Path<? extends PolicyStatus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPolicyStatus(PathMetadata metadata) {
        super(PolicyStatus.class, metadata);
    }

}

