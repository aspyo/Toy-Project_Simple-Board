package hello.board.domain.user.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class SignupForm {
    private String username;
    private String loginId;
    private String password;
    private String passwordConfirm;
}
