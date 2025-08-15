package com.nhnacademy.taskapi.category;

import com.nhnacademy.taskapi.category.domain.Category;
import com.nhnacademy.taskapi.category.repository.CategoryRepository;
import com.nhnacademy.taskapi.config.QuerydslConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Slf4j
@DataJpaTest
@Import(QuerydslConfig.class)
public class CategoryRefactorRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        // 국내도서
        Category domestic = new Category();
        domestic.setName("국내도서");
        Category savedDomestic = categoryRepository.save(domestic);

        Category travel = new Category();
        travel.setName("여행");
        travel.setParentCategory(savedDomestic);
        Category savedTravel = categoryRepository.save(travel);

        Category europeTravel = new Category();
        europeTravel.setName("유럽여행");
        europeTravel.setParentCategory(savedTravel);
        categoryRepository.save(europeTravel);

        // 해외도서
        Category foreign = new Category();
        foreign.setName("해외도서");
        Category savedForeign = categoryRepository.save(foreign);

        Category history = new Category();
        history.setName("역사");
        history.setParentCategory(savedForeign);
        Category savedHistory = categoryRepository.save(history);

        Category renaissance = new Category();
        renaissance.setName("르네상스");
        renaissance.setParentCategory(savedHistory);
        categoryRepository.save(renaissance);
    }


    @Test
    @DisplayName("findAll Test - SQL 확인용")
    void findAllTest() {
        categoryRepository.findAll();
    }

    @Test
    @DisplayName("1차 리팩토링")
    void findByFirstRefactor() {
        categoryRepository.findAllForTree();
    }

    @Test
    @DisplayName("2차 리팩토링")
    void findBySecondRefactor() {
        categoryRepository.findAllForTree();
    }

}
