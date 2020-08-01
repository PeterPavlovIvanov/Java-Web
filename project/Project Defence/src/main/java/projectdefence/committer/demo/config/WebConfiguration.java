package projectdefence.committer.demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import projectdefence.committer.demo.models.interceptors.LoginInterceptor;
import projectdefence.committer.demo.models.interceptors.StatsInterceptor;

@Component
public class WebConfiguration implements WebMvcConfigurer {

    private StatsInterceptor statsInterceptor;
    private LoginInterceptor loginInterceptor;

    public WebConfiguration(StatsInterceptor statsInterceptor, LoginInterceptor loginInterceptor) {
        this.statsInterceptor = statsInterceptor;
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(statsInterceptor);
        registry.addInterceptor(loginInterceptor);
    }

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);

    }
}
