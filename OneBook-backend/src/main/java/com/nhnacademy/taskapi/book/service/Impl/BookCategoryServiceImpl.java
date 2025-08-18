package com.nhnacademy.taskapi.book.service.Impl;


import com.nhnacademy.taskapi.book.domain.Book;
import com.nhnacademy.taskapi.book.domain.BookCategory;
import com.nhnacademy.taskapi.book.exception.BookCategoryDuplicateException;
import com.nhnacademy.taskapi.book.exception.BookCategoryNotFoundException;
import com.nhnacademy.taskapi.book.exception.BookNotFoundException;
import com.nhnacademy.taskapi.book.repository.BookCategoryRepository;
import com.nhnacademy.taskapi.book.repository.BookRepository;
import com.nhnacademy.taskapi.book.service.BookCategoryService;
import com.nhnacademy.taskapi.book.service.BookService;
import com.nhnacademy.taskapi.category.domain.Category;
import com.nhnacademy.taskapi.category.dto.CategoryResponseDto;
import com.nhnacademy.taskapi.category.exception.CategoryNotFoundException;
import com.nhnacademy.taskapi.category.repository.CategoryClosureRepository;
import com.nhnacademy.taskapi.category.repository.CategoryRepository;
import com.nhnacademy.taskapi.book.dto.BookCategorySaveDTO;
import com.nhnacademy.taskapi.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookCategoryServiceImpl implements BookCategoryService {
    private final BookCategoryRepository bookCategoryRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryClosureRepository categoryClosureRepository;

    @Transactional
    @Override
    public BookCategory save(BookCategorySaveDTO dto) {


        BookCategory bookCategory = bookCategoryRepository.findByBook_bookIdAndCategory_categoryId(dto.getBookId(),dto.getCategoryId());

        if(Objects.isNull(bookCategory)){
            Book book = bookRepository.findById(dto.getBookId()).orElseThrow(() -> new BookNotFoundException("Book Not Found !"));
            Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category Not Found !"));

            bookCategory = new BookCategory();
            bookCategory.setBook(book);
            bookCategory.setCategory(category);
        }else{
            throw new BookCategoryDuplicateException("Book Category already exists !");
        }

        return bookCategoryRepository.save(bookCategory);
    }

    @Override
    public Page<BookCategory> getBookByCategory(int categoryId, Pageable pageable) {
        Page<BookCategory> list = bookCategoryRepository.findAllByCategory_CategoryIdOrderByBook_AmountDesc(categoryId, pageable);
        if (Objects.isNull(list)) {
            throw new BookCategoryNotFoundException("Book Category Not Found !");
        }

        return list;
    }

    @Override
    public BookCategory getBookCategoryByBookId(long bookId) {
        return bookCategoryRepository.findByBook_bookId(bookId);
    }

    // 카테고리 사이드 바
    @Override
    public CategoryResponseDto getSideBarCategory(int selectedCategoryId) {
        // 선택한 카테고리의 직계(루트의 자식) 찾기
        Integer anchorId = categoryClosureRepository.findTopChildAncestorId(selectedCategoryId);

        if (anchorId == null) {
            anchorId = selectedCategoryId;
        }

        // 찾은 직계의 서브 트리 가져오기
        List<Category> flat = categoryClosureRepository.findSubtree(anchorId);
        Map<Integer, List<Category>> byParent = flat.stream()
                .filter(c -> c.getParentCategory() != null)
                .collect(Collectors.groupingBy(c -> c.getParentCategory().getCategoryId()));

        Integer finalAnchorId = anchorId;
        Category anchor = flat.stream()
                .filter(c -> c.getCategoryId() == finalAnchorId)
                .findFirst().orElseThrow();

        return toDtoTree(anchor, byParent);
    }

    private CategoryResponseDto toDtoTree(Category c, Map<Integer, List<Category>> byParent) {
        List<Category> children = byParent.getOrDefault(c.getCategoryId(), List.of());
        return new CategoryResponseDto(
                c.getCategoryId(),
                c.getName(),
                c.isStatus(),
                children.stream()
                        .map(ch -> toDtoTree(ch, byParent))
                        .collect(Collectors.toList())
        );
    }
}
