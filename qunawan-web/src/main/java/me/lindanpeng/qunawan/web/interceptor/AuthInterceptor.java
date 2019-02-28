package me.lindanpeng.qunawan.web.interceptor;

import me.lindanpeng.qunawan.web.dto.SessionData;
import me.lindanpeng.qunawan.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    Logger logger= LoggerFactory.getLogger(AuthInterceptor.class);
    @Autowired
    UserService userService;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       SessionData sessionData= (SessionData) request.getSession().getAttribute("loginedUser");
       if (sessionData==null|| StringUtils.isEmpty(sessionData.getEmail()))
           return false;
       logger.info("access user:{}",sessionData.getEmail());
       return true;
    }
}
