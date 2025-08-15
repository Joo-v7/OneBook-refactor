package com.nhnacademy.taskapi.point.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPointLog is a Querydsl query type for PointLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointLog extends EntityPathBase<PointLog> {

    private static final long serialVersionUID = -1798113442L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPointLog pointLog = new QPointLog("pointLog");

    public final NumberPath<Integer> afterPointAmount = createNumber("afterPointAmount", Integer.class);

    public final QPoint point;

    public final NumberPath<Integer> pointLogAmount = createNumber("pointLogAmount", Integer.class);

    public final NumberPath<Long> pointLogId = createNumber("pointLogId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> pointLogUpdatedAt = createDateTime("pointLogUpdatedAt", java.time.LocalDateTime.class);

    public final EnumPath<PointLogUpdatedType> pointLogUpdatedType = createEnum("pointLogUpdatedType", PointLogUpdatedType.class);

    public QPointLog(String variable) {
        this(PointLog.class, forVariable(variable), INITS);
    }

    public QPointLog(Path<? extends PointLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPointLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPointLog(PathMetadata metadata, PathInits inits) {
        this(PointLog.class, metadata, inits);
    }

    public QPointLog(Class<? extends PointLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.point = inits.isInitialized("point") ? new QPoint(forProperty("point"), inits.get("point")) : null;
    }

}

