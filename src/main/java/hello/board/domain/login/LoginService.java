package hello.board.domain.login;

import hello.board.domain.user.application.UserService;
import hello.board.domain.user.domain.User;
import hello.board.domain.user.dto.LoginForm;
import hello.board.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    // 특정 아이디,비밀번호의 유저 조회 후 있으면 조회한 유저를, 없으면 null 반환
    public User login(LoginForm form) {
        return userRepository.findUserByLoginId(form.getLoginId())
                .filter(user -> user.getPassword().equals(form.getPassword()))
                .orElse(null);
    }

}
