package hello.board.domain.user.application;

import hello.board.domain.user.domain.User;
import hello.board.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    //회원가입
    @Transactional
    public Long join(User user) {

        //아이디 중복 검증
        validateDuplicateUserLoginId(user.getLoginId());

        //비밀번호 중복 검증
        validateDuplicateUserPassword(user.getPassword());

        //회원가입 성공
        User savedUser = userRepository.save(user);

        return savedUser.getId();
    }

    //모든 유저 조회
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //특정 유저 조회
    public User findUser(Long id) {
        Optional<User> findUser = userRepository.findById(id);
        return findUser.orElseThrow(() -> new EntityNotFoundException("해당 유저를 찾을 수 없습니다."));
    }

    //회원 탈퇴
    public void delete(Long userId) {
        Optional<User> findUser = userRepository.findById(userId);
        userRepository.delete(findUser.orElseThrow(() -> new EntityNotFoundException("해당 유저를 찾을 수 없습니다.")));
    }

    private void validateDuplicateUserLoginId(String loginId) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getLoginId().equals(loginId)) {
                throw new IllegalStateException("이미 등록된 아이디입니다.");
            }
        }
    }

    private void validateDuplicateUserPassword(String password) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getPassword().equals(password)) {
                throw new IllegalStateException("이미 등록된 비밀번호입니다.");
            }
        }
    }


}
