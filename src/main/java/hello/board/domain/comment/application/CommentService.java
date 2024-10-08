package hello.board.domain.comment.application;

import hello.board.domain.comment.domain.Comment;
import hello.board.domain.comment.repository.CommentRepository;
import hello.board.domain.post.domain.Post;
import hello.board.domain.user.domain.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    /**
     * 댓글 생성
     */
    @Transactional
    public Long createComment(User user, Post post, String content) {
        Comment newComment = Comment.builder()
                .user(user)
                .content(content)
                .build();
        newComment.setPost(post);

        Comment savedComment = commentRepository.save(newComment);
        return savedComment.getId();
    }

    /**
     * 댓글 삭제
     */
    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.delete(findComment(commentId));
    }

    /**
     * 모든 댓글 조회
     */
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    /**
     * 특정 댓글 조회
     */
    public Comment findComment(Long id) {
        Optional<Comment> byId = commentRepository.findById(id);
        return byId.orElseThrow(() -> new EntityNotFoundException("해당 댓글을 찾을 수 없습니다."));
    }
}
