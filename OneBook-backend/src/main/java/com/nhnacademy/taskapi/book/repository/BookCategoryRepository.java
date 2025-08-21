package com.nhnacademy.taskapi.book.repository;

import com.nhnacademy.taskapi.aop.TimeTrace;
import com.nhnacademy.taskapi.book.domain.Book;
import com.nhnacademy.taskapi.book.domain.BookCategory;
import com.nhnacademy.taskapi.book.dto.BookListItemDTO;
import com.nhnacademy.taskapi.category.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

    /**
     * 카테고리 별 도서 목록 조회
     * @param categoryId
     * @param pageable
     * @return Page<BookCategory>
     */
    Page<BookCategory> findAllByCategory_CategoryIdOrderByBook_AmountDesc(int categoryId, Pageable pageable);

    BookCategory findByBook_bookIdAndCategory_categoryId(Long bookId, Integer categoryId);

    BookCategory findByBook_bookId(long bookId);

    /**
     * 리팩토링 - 카테고리 별 도서 목록 조회
     * @param categoryId
     * @param pageable
     * @return Page<BookListItemDTO>
     */
    @TimeTrace
    @Query(value = """
    select new com.nhnacademy.taskapi.book.dto.BookListItemDTO(
    bc.bookCategoryId, b.bookId, b.title, b.price, b.salePrice, b.amount)
    from BookCategory bc
    inner join bc.book b
    where bc.category.categoryId = :categoryId
    """)
    Page<BookListItemDTO> findBookList(@Param("categoryId") int categoryId, Pageable pageable);

}
