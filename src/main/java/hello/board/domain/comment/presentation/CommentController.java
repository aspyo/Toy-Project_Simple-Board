package hello.board.domain.comment.presentation;

import hello.board.domain.comment.application.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    /**
     * 게시글 삭제 요청 처리 메서드
     */
    @PostMapping("/comment/delete/{id}")
    public String deleteComment(@PathVariable("id") Long commentId, @RequestParam("post_id") Long postId) {
        commentService.deleteComment(commentId);

        return "redirect:/post/" + postId;
    }
}
