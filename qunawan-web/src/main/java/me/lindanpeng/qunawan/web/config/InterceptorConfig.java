package me.lindanpeng.qunawan.web.config;

import me.lindanpeng.qunawan.web.interceptor.AuthInterceptor;
import me.lindanpeng.qunawan.web.interceptor.RequestInfoInterceptor;
import org.omg.PortableInterceptor.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    AuthInterceptor authInterceptor;
    @Autowired
    RequestInfoInterceptor requestInfoInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInfoInterceptor).addPathPatterns("/*");
//        registry.addInterceptor(authInterceptor)
//                .addPathPatterns("/*")
//                .excludePathPatterns("/doLogin")
//                .excludePathPatterns("/register")
//                .excludePathPatterns("/doLogin");

    }
}
