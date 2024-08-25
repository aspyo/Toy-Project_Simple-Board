package hello.board.domain.category.application;

import hello.board.domain.category.domain.Category;
import hello.board.domain.category.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * 카테고리 생성
     */
    public Long createCategory(Category category) {
        Category saved = categoryRepository.save(category);
        return saved.getId();
    }

    /**
     * 카테고리 삭제
     */
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    /**
     * 모든 카테고리 조회
     */
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * 특정 카테고리 조회
     */
    public Category findCategory(Long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        return byId.orElseThrow(() -> new EntityNotFoundException("해당 카테고리를 찾을 수 없습니다."));
    }
}
