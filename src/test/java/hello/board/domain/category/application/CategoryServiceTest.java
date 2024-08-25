package hello.board.domain.category.application;

import hello.board.domain.category.domain.Category;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    EntityManager em;

    @Test
    void 카테고리생성() throws Exception {
        //given
        Category category = Category.builder()
                .categoryName("CategoryA")
                .description("테스트 카테고리A 입니다.")
                .build();

        //when
        Long createdId = categoryService.createCategory(category);
        Category findCategory = categoryService.findCategory(createdId);

        //then
        assertThat(findCategory.getCategoryName()).isEqualTo(category.getCategoryName());
        assertThat(findCategory.getDescription()).isEqualTo(category.getDescription());
    }
    
}