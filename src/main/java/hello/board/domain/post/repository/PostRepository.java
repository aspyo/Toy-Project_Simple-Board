package hello.board.domain.post.repository;

import hello.board.domain.post.domain.Post;
import hello.board.domain.post.repository.web.CustomRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, CustomRepository {

}
