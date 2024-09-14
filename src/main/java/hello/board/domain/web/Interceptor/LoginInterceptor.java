package hello.board.domain.web.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("로그인 인터셉터 로직 실행 {}", request.getRequestURI());

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("loginUser") == null) {
            log.info("미인증 사용자 요청");
            // 로그인으로 redirect
            response.sendRedirect("/login?redirectURL=" + request.getRequestURI() + "?" + request.getQueryString());
            return false;
        }

        // 로그인된 유저이므로 정상로직 실행
        return true;
    }
}
