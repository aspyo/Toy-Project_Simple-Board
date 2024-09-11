package hello.board.domain.post.presentation;

import hello.board.domain.category.application.CategoryService;
import hello.board.domain.category.domain.Category;
import hello.board.domain.post.application.PostService;
import hello.board.domain.post.domain.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;

    @GetMapping("/category/{category_id}")
    public String category_post(@PathVariable("category_id") Long categoryId, @RequestParam(defaultValue = "1", value = "page") int page, Model model) {
        Category category = categoryService.findCategory(categoryId);
        Page<Post> postPage = postService.findPostsByCategory(category, page);
        List<Post> posts = postPage.getContent();

        log.info("게시글 조회 개수 = {}", posts.size());

        model.addAttribute("category", category);
        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", postPage.getNumber() + 1);
        model.addAttribute("totalPages", postPage.getTotalPages());

        return "post/category-post";
    }

    @GetMapping("/post/{post_id}")
    public String postDetail(@PathVariable("post_id") Long postId, Model model) {
        Post findPost = postService.findPost(postId);
        model.addAttribute("post", findPost);
        model.addAttribute("comments", findPost.getComments());

        return "post/post-detail";
    }
}
