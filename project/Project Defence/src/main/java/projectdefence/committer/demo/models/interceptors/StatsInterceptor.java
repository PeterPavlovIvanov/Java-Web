package projectdefence.committer.demo.models.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import projectdefence.committer.demo.services.impl.StatsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class StatsInterceptor implements HandlerInterceptor {

  private StatsService statsService;

  public StatsInterceptor(StatsService statsService) {
    this.statsService = statsService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
          throws Exception {
    statsService.incRequestCount();
    return true;
  }
}
