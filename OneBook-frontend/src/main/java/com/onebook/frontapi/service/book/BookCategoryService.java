package com.onebook.frontapi.service.book;

import com.onebook.frontapi.dto.book.BookCategoryDTO;
import com.onebook.frontapi.dto.category.CategoryResponseDto;
import com.onebook.frontapi.feign.book.BookCategoryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCategoryService {
    private final BookCategoryClient bookCategoryClient;

    // 카테고리 별 도서 목록 조회
    public Page<BookCategoryDTO> getAllBookCategories(int categoryId, Pageable pageable) {
        return bookCategoryClient.getAllBookCategories(categoryId, pageable);
    }

    public BookCategoryDTO getBookCategoryByBookId(long bookId) {
        return bookCategoryClient.getBookCategory(bookId);
    }

    // 카테고리 사이드바
    public CategoryResponseDto getCategorySidebarByCategoryId(long categoryId) {
        return bookCategoryClient.getCategorySidebar(categoryId);
    }
}
