package hello.board.domain.user.presentation;

import hello.board.domain.login.LoginService;
import hello.board.domain.user.application.UserService;
import hello.board.domain.user.domain.User;
import hello.board.domain.user.dto.LoginForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult, HttpServletRequest request) {

        // 잘못된 로그인 입력 처리
        if (bindingResult.hasErrors()) {
            return "user/login";
        }

        // 해당 아이디, 비밀번호에 맞는 유저가 없는 경우 처리
        User loginUser = loginService.login(form);
        if (loginUser == null) {
            bindingResult.reject(null, "아이디 또는 비밀번호가 맞지 않습니다.");
            return "user/login";
        }

        //로그인 성공
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", loginUser);

        return "redirect:/";
    }

//    @GetMapping("/signup")
//    public String signupForm() {
//
//    }
}
