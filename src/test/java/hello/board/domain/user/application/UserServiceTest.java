package hello.board.domain.user.application;

import hello.board.domain.user.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void 회원가입() throws Exception {

        //given
        User userA = User.builder()
                .loginId("testId")
                .password("testPassword")
                .username("userA")
                .build();

        //when
        Long joinedId = userService.join(userA);
        User joinedUser = userService.findUser(joinedId);

        //then
        assertThat(joinedUser.getUsername()).isEqualTo(userA.getUsername());
        assertThat(joinedUser.getLoginId()).isEqualTo(userA.getLoginId());
        assertThat(joinedUser.getPassword()).isEqualTo(userA.getPassword());
    }

    @Test
    void 아이디중복검증() throws Exception {
        //given
        User userA = User.builder()
                .loginId("testId")
                .password("testPassword")
                .username("userA")
                .build();

        User userB = User.builder()
                .username("userB")
                .loginId("testId")
                .password("testPasswordB")
                .build();

        //when
        userService.join(userA);

        //then
        assertThrows(IllegalStateException.class, () -> userService.join(userB));
    }

    @Test
    void 비밀번호중복검증() throws Exception {
        //given
        User userA = User.builder()
                .loginId("testId")
                .password("testPassword")
                .username("userA")
                .build();

        User userB = User.builder()
                .username("userB")
                .loginId("testIdB")
                .password("testPassword")
                .build();

        //when
        userService.join(userA);

        //then
        assertThrows(IllegalStateException.class, () -> userService.join(userB));
    }

    @Test
    void 모든회원조회() throws Exception {
        //given
        User userA = User.builder()
                .loginId("testId")
                .password("testPassword")
                .username("userA")
                .build();

        User userB = User.builder()
                .username("userB")
                .loginId("testIdB")
                .password("testPasswordB")
                .build();

        //when
        userService.join(userA);
        userService.join(userB);
        List<User> users = userService.findAll();

        //then
        assertThat(users.size()).isEqualTo(2);

    }

}