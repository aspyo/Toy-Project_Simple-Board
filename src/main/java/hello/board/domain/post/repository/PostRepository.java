package hello.board.domain.post.repository;

import hello.board.domain.category.domain.Category;
import hello.board.domain.post.domain.Post;
import hello.board.domain.post.repository.web.CustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, CustomRepository {

    Page<Post> findByCategory(Category category, Pageable pageable);
}
