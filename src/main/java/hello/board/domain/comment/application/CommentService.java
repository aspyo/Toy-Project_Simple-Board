package hello.board.domain.comment.application;

import hello.board.domain.comment.domain.Comment;
import hello.board.domain.comment.repository.CommentRepository;
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
    public Long createComment(Comment comment) {
        Comment saved = commentRepository.save(comment);
        return saved.getId();
    }

    /**
     * 댓글 삭제
     */
    @Transactional
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
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
