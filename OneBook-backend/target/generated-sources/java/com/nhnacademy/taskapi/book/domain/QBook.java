package com.nhnacademy.taskapi.book.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBook is a Querydsl query type for Book
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBook extends EntityPathBase<Book> {

    private static final long serialVersionUID = -1710369392L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBook book = new QBook("book");

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final NumberPath<Long> bookId = createNumber("bookId", Long.class);

    public final StringPath content = createString("content");

    public final StringPath description = createString("description");

    public final StringPath isbn13 = createString("isbn13");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final DatePath<java.time.LocalDate> pubdate = createDate("pubdate", java.time.LocalDate.class);

    public final com.nhnacademy.taskapi.publisher.domain.QPublisher publisher;

    public final NumberPath<Integer> salePrice = createNumber("salePrice", Integer.class);

    public final BooleanPath status = createBoolean("status");

    public final StringPath title = createString("title");

    public final NumberPath<Long> views = createNumber("views", Long.class);

    public QBook(String variable) {
        this(Book.class, forVariable(variable), INITS);
    }

    public QBook(Path<? extends Book> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBook(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBook(PathMetadata metadata, PathInits inits) {
        this(Book.class, metadata, inits);
    }

    public QBook(Class<? extends Book> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.publisher = inits.isInitialized("publisher") ? new com.nhnacademy.taskapi.publisher.domain.QPublisher(forProperty("publisher")) : null;
    }

}

