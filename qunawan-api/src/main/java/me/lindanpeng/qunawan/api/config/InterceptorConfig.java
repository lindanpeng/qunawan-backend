package me.lindanpeng.qunawan.api.config;

import me.lindanpeng.qunawan.api.interceptor.AuthInterceptor;
import me.lindanpeng.qunawan.api.interceptor.RequestInfoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    AuthInterceptor authInterceptor;
    @Autowired
    RequestInfoInterceptor requestInfoInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInfoInterceptor).addPathPatterns("/**");
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/api/*")
                .excludePathPatterns("/api/doLogin")
                .excludePathPatterns("/api/register")
                .excludePathPatterns("/api/doLogin");


    }
}
