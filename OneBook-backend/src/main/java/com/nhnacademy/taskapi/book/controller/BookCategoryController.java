package com.nhnacademy.taskapi.book.controller;

import com.nhnacademy.taskapi.book.domain.BookCategory;
import com.nhnacademy.taskapi.book.dto.BookCategorySaveDTO;
import com.nhnacademy.taskapi.book.dto.BookListItemDTO;
import com.nhnacademy.taskapi.book.service.BookCategoryService;
import com.nhnacademy.taskapi.category.dto.CategoryResponseDto;
import com.nhnacademy.taskapi.category.service.CategoryClosureService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task/book/category")
@Tag(name = "Book", description = "도서를 생성, 수정, 삭제, 조회, 관리등 각종 도서 관련 기능")  // API 그룹 설명 추가
public class BookCategoryController {
    private final BookCategoryService bookCategoryService;
    private final CategoryClosureService categoryClosureService;

    @PostMapping
    public ResponseEntity<?> createBookCategory(@Valid @RequestBody BookCategorySaveDTO bookCategorySaveDTO) {
        BookCategory bookCategory = bookCategoryService.save(bookCategorySaveDTO);
        return ResponseEntity.ok().body(bookCategory);
    }

    /**
     * 카테고리 별 도서 가져오는 요청
     * @param categoryId
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<Page<BookCategory>> getAllBookCategories(@RequestParam int categoryId, Pageable pageable) {
        Page<BookCategory> bookCategories = bookCategoryService.getBookByCategory(categoryId, pageable);
        return ResponseEntity.ok().body(bookCategories);
    }

    /**
     * 리팩토링 - 카테고리 별 도서 목록 조회
     * @param categoryId
     * @param pageable
     * @return Page<BookListItemDTO>
     */
    @GetMapping("/re")
    public ResponseEntity<Page<BookListItemDTO>> getBookList(@RequestParam int categoryId, Pageable pageable) {
        Page<BookListItemDTO> bookListItemDTOS = bookCategoryService.getBookList(categoryId, pageable);
        return ResponseEntity.ok().body(bookListItemDTOS);
    }

    @GetMapping("{bookid}")
    public ResponseEntity<BookCategory> getBookCategory(@PathVariable Long bookid) {
        BookCategory bookCategory = bookCategoryService.getBookCategoryByBookId(bookid);
        return ResponseEntity.ok().body(bookCategory);
    }

    /**
     * 카테고리별 도서 목록 페이지의 사이드 카테고리 바
     * @return
     */
    @GetMapping("/book-categories/sidebar/{categoryId}")
    public ResponseEntity<CategoryResponseDto> getCategorySidebar(@PathVariable int categoryId) {
        CategoryResponseDto categoryResponseDto = bookCategoryService.getSideBarCategory(categoryId);
        return ResponseEntity.ok(categoryResponseDto);
    }



}
