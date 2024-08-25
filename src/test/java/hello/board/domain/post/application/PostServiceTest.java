package hello.board.domain.post.application;

import hello.board.domain.category.domain.Category;
import hello.board.domain.post.domain.Post;
import hello.board.domain.user.application.UserService;
import hello.board.domain.user.domain.User;
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
@Rollback(value = false)
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void 게시글생성() throws Exception {
        //given
        User userA = User.builder()
                .loginId("testId")
                .password("testPassword")
                .username("userA")
                .build();

        em.persist(userA);

        Category category = Category.builder()
                .categoryName("백엔드")
                .description("백엔드 카테고리입니다.")
                .build();

        em.persist(category);

        //when
        Post postA = Post.builder()
                .title("postA")
                .content("게시글 테스트입니다.")
                .category(category)
                .build();
        postA.setUser(userA);

        Long savedId = postService.createPost(postA);

        em.flush();
        em.clear();

        Post findPost = postService.findPost(savedId);

        //then
        assertThat(findPost.getTitle()).isEqualTo(postA.getTitle());
        assertThat(findPost.getContent()).isEqualTo(postA.getContent());
        assertThat(findPost.getUser().getUsername()).isEqualTo(postA.getUser().getUsername());
        assertThat(findPost.getCategory().getCategoryName()).isEqualTo(postA.getCategory().getCategoryName());

    }

}