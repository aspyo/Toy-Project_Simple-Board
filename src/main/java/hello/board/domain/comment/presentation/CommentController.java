package hello.board.domain.comment.presentation;

import hello.board.domain.comment.application.CommentService;
import hello.board.domain.comment.domain.Comment;
import hello.board.domain.post.application.PostService;
import hello.board.domain.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    private final PostService postService;

    /**
     * 댓글 생성 메서드
     */
    @PostMapping("/comment")
    public String createComment(@RequestParam("content") String content,
                                @RequestParam("post_id") Long postId,
                                HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }
        User loginUser = (User) session.getAttribute("loginUser");

        commentService.createComment(loginUser, postService.findPost(postId), content);

        return "redirect:" + request.getHeader("Referer");
    }

    /**
     * 게시글 삭제 요청 처리 메서드
     */
    @PostMapping("/comment/delete/{id}")
    public String deleteComment(@PathVariable("id") Long commentId, @RequestParam("post_id") Long postId) {
        commentService.deleteComment(commentId);

        return "redirect:/post/" + postId;
    }
}
