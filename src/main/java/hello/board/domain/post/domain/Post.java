package hello.board.domain.post.domain;

import hello.board.domain.category.domain.Category;
import hello.board.domain.comment.domain.Comment;
import hello.board.domain.common.BaseEntity;
import hello.board.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    private int likes = 0;

    private int dislikes = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    protected Post() {
    }

    @Builder
    public Post(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    /**
     * 양방향 연관관계 편의 메서드
     */
    public void setUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }

    /**
     * 비지니스 메서드
     */
    public void increaseLike() {
        likes++;
    }

    public void decreaseLike() {
        likes--;
    }

    public void increaseDislike() {
        dislikes++;
    }

    public void decreaseDislike() {
        dislikes--;
    }

    public void changeCategory(Category category) {
        this.category = category;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }


}
