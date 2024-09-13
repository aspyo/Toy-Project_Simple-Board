package hello.board.domain.main;

import hello.board.domain.category.application.CategoryService;
import hello.board.domain.category.domain.Category;
import hello.board.domain.post.application.PostService;
import hello.board.domain.post.domain.Post;
import hello.board.domain.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final CategoryService categoryService;
    private final PostService postService;

    @GetMapping("/")
    public String mainPage(HttpServletRequest request, Model model) {
        List<Category> categories = categoryService.findAll();
        List<Post> posts = postService.findMainPagePost2();

        model.addAttribute("categories", categories);
        model.addAttribute("posts", posts);

        User loginUser = null;
        HttpSession session = request.getSession(false);
        if (session != null) {
            loginUser = (User) session.getAttribute("loginUser");
        }

        model.addAttribute("loginUser", loginUser);

        return "main/index";
    }
}
