package hello.board.domain.comment.domain;

import hello.board.domain.common.BaseEntity;
import hello.board.domain.post.domain.Post;
import hello.board.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    protected Comment() {

    }

    @Builder
    public Comment(String content, User user) {
        this.content = content;
        this.user = user;
    }

    /**
     * 양방향 연관관계 편의 메서드
     */
    public void setPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }
}
