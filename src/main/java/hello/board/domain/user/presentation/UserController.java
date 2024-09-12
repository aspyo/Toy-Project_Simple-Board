package hello.board.domain.user.presentation;

import hello.board.domain.login.LoginService;
import hello.board.domain.user.application.UserService;
import hello.board.domain.user.domain.User;
import hello.board.domain.user.dto.LoginForm;
import hello.board.domain.user.dto.SignupForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String login(@Valid @ModelAttribute("loginForm") LoginForm form,
                        BindingResult bindingResult,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes) {

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

        redirectAttributes.addAttribute("loginUser", loginUser);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("signupForm") SignupForm form, BindingResult bindingResult) {
        // 비밀번호 = 비밀번호확인 검증
        if(!form.getPassword().equals(form.getPasswordConfirm())){
            bindingResult.reject(null, "비밀번호가 맞지 않습니다.");

            return "user/signup";
        }

        try {
            User newUser = User.builder()
                    .username(form.getUsername())
                    .loginId(form.getLoginId())
                    .password(form.getPassword())
                    .build();

            userService.join(newUser);
        } catch (IllegalStateException e) {
            bindingResult.reject(null, e.getMessage());
            return "user/signup";
        }

        //회원가입 성공
        return "redirect:/login";

    }


}
