package hello.board.domain.comment.application;

import hello.board.domain.category.domain.Category;
import hello.board.domain.comment.domain.Comment;
import hello.board.domain.post.domain.Post;
import hello.board.domain.user.domain.User;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Autowired
    EntityManager em;

    @BeforeEach
    void init() {
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

        Post postA = Post.builder()
                .title("postA")
                .content("게시글 테스트입니다.")
                .category(category)
                .build();
        postA.setUser(userA);

        em.persist(postA);
    }

    @Test
    void 댓글생성() throws Exception {
        //given
        Comment comment = Comment.builder()
                .content("테스트 댓글 내용입니다.")
                .user(em.find(User.class, 1L))
                .build();

        comment.setPost(em.find(Post.class, 1L));
        commentService.createComment(comment);

        //when
        Comment findComment = commentService.findComment(comment.getId());

        //then
        Assertions.assertThat(findComment.getContent()).isEqualTo(comment.getContent());
        Assertions.assertThat(findComment.getUser().getUsername()).isEqualTo(comment.getUser().getUsername());
        Assertions.assertThat(findComment.getPost().getTitle()).isEqualTo(comment.getPost().getTitle());

    }
}