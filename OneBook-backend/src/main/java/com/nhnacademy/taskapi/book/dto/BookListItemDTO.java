package com.nhnacademy.taskapi.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookListItemDTO {
    private long bookCategoryId;
    private long bookId;
    private String title;
    private int price;
    private int salePrice;
    private long amount;
}
