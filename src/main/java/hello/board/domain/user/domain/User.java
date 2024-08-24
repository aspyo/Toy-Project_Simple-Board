package hello.board.domain.user.domain;

import hello.board.domain.comment.domain.Comment;
import hello.board.domain.common.BaseEntity;
import hello.board.domain.post.domain.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "Users")
public class User extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String username;

    private String login_id;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    protected User() {
    }

    @Builder
    public User(String username, String login_id, String password) {
        this.username = username;
        this.login_id = login_id;
        this.password = password;
    }
}
