package hello.board.domain.post.presentation;

import hello.board.domain.category.application.CategoryService;
import hello.board.domain.category.domain.Category;
import hello.board.domain.post.application.PostService;
import hello.board.domain.post.domain.Post;
import hello.board.domain.post.dto.PostForm;
import hello.board.domain.user.application.UserService;
import hello.board.domain.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final UserService userService;

    /**
     * 카테고리별 게시글 페이지
     */
    @GetMapping("/category/{category_id}")
    public String category_post(@PathVariable("category_id") Long categoryId, @RequestParam(defaultValue = "1", value = "page") int page, Model model) {
        Category category = categoryService.findCategory(categoryId);
        Page<Post> postPage = postService.findPostsByCategory(category, page);
        List<Post> posts = postPage.getContent();

        model.addAttribute("category", category);
        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", postPage.getNumber() + 1);
        model.addAttribute("totalPages", Math.max(postPage.getTotalPages(), 1));

        return "post/category-post";
    }

    /**
     * 특정 id의 게시글 조회
     */
    @GetMapping("/post/{post_id}")
    public String postDetail(@PathVariable("post_id") Long postId, HttpServletRequest request, Model model) {
        Post findPost = postService.findPost(postId);

        HttpSession session = request.getSession(false);

        // loginUser = 세션이 없거나, 세션은 있어도 해당 세션에 저장된 유저가 없을 경우 null, 있을 경우 해당 유저
        User loginUser = (session == null) ? null : (User) session.getAttribute("loginUser");

        model.addAttribute("post", findPost);
        model.addAttribute("comments", findPost.getComments());
        model.addAttribute("postUser", findPost.getUser());
        model.addAttribute("loginUser", loginUser);

        return "post/post-detail";
    }

    @GetMapping("/create")
    public String createForm(@RequestParam("category") Long categoryId, Model model) {

        model.addAttribute("form", new PostForm());
        model.addAttribute("category_id", categoryId);

        return "post/create-post";
    }

    @PostMapping("/create")
    public String createPost(@RequestParam("category") Long categoryId,
                             @ModelAttribute PostForm form,
                             HttpServletRequest request) {
        log.info("게시글 생성 post 요청 호출");

        Post newPost = Post.builder()
                .title(form.getTitle())
                .content(form.getContent())
                .category(categoryService.findCategory(categoryId))
                .build();

        User sessionUser = (User) request.getSession().getAttribute("loginUser");
        User loginUser = userService.findUser(sessionUser.getId());

        newPost.setUser(loginUser);

        postService.createPost(newPost);

        return "redirect:/category/" + categoryId;
    }

    @GetMapping("/post/edit/{id}")
    public String editForm(@PathVariable("id") Long postId, Model model) {
        Post editPost = postService.findPost(postId);
        PostForm form = new PostForm(editPost.getId(), editPost.getTitle(), editPost.getContent());
        model.addAttribute("form", form);
        model.addAttribute("category_id", editPost.getCategory().getId());

        return "post/post-edit";
    }

    @PostMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, @ModelAttribute PostForm form) {
        log.info("게시글 수정 post 요청 로직 실행!! form = {}", form);

        postService.editPost(id, form.getTitle(), form.getContent());

        return "redirect:/post/{id}";
    }

    @PostMapping("/post/delete/{id}")
    public String delete(@PathVariable("id") Long postId,
                         @RequestParam("category_id") Long categoryId,
                         RedirectAttributes redirectAttributes) {
        postService.deletePost(postService.findPost(postId));
        redirectAttributes.addAttribute("category_id", categoryId);

        return "redirect:/category/{category_id}";
    }
}
