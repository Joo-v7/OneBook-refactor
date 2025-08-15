package com.nhnacademy.taskapi.payment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPaymentMethod is a Querydsl query type for PaymentMethod
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaymentMethod extends EntityPathBase<PaymentMethod> {

    private static final long serialVersionUID = 454417863L;

    public static final QPaymentMethod paymentMethod = new QPaymentMethod("paymentMethod");

    public final StringPath cardAcquirerCode = createString("cardAcquirerCode");

    public final NumberPath<Integer> cardAmount = createNumber("cardAmount", Integer.class);

    public final StringPath cardApproveNo = createString("cardApproveNo");

    public final NumberPath<Integer> cardInstallmentPlanMonths = createNumber("cardInstallmentPlanMonths", Integer.class);

    public final BooleanPath cardIsInterestFree = createBoolean("cardIsInterestFree");

    public final StringPath cardIssuerCode = createString("cardIssuerCode");

    public final StringPath cardNumber = createString("cardNumber");

    public final StringPath cardOwnerType = createString("cardOwnerType");

    public final StringPath cardType = createString("cardType");

    public final StringPath method = createString("method");

    public final StringPath paymentKey = createString("paymentKey");

    public final StringPath type = createString("type");

    public QPaymentMethod(String variable) {
        super(PaymentMethod.class, forVariable(variable));
    }

    public QPaymentMethod(Path<? extends PaymentMethod> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPaymentMethod(PathMetadata metadata) {
        super(PaymentMethod.class, metadata);
    }

}

