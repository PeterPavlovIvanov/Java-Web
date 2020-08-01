package projectdefence.committer.demo.models.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import projectdefence.committer.demo.services.impl.LastLoggedInService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private LastLoggedInService lastLoggedInService;

    public LoginInterceptor(LastLoggedInService lastLoggedInService) {
        this.lastLoggedInService = lastLoggedInService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (request.getServletPath().equals("/users/login") && request.getMethod().equals("POST")) {
            this.lastLoggedInService.setLoggedOn(Instant.now());
        }
    }
}