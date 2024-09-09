package hello.board.domain.post.repository.web;

import hello.board.domain.post.domain.Post;

import java.util.List;

public interface CustomRepository {

    List<Post> findMainPost();
}
