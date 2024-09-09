package hello.board.domain.post.repository.web;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.board.domain.post.domain.Post;
import hello.board.domain.post.domain.QPost;
import jakarta.persistence.EntityManager;

import java.util.List;

import static hello.board.domain.post.domain.QPost.*;

public class CustomRepositoryImpl implements CustomRepository{

    private final JPAQueryFactory queryFactory;

    public CustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Post> findMainPost() {
        List<Post> results = queryFactory
                .select(post)
                .from(post)
                .orderBy(post.createdAt.desc())
                .offset(0)
                .limit(3)
                .fetch();

        return results;
    }
}
