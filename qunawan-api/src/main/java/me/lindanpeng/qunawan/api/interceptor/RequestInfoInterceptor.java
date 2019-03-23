package me.lindanpeng.qunawan.api.interceptor;

import me.lindanpeng.qunawan.core.util.MapUtil;
import me.lindanpeng.qunawan.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInfoInterceptor extends HandlerInterceptorAdapter {
    Logger logger= LoggerFactory.getLogger(AuthInterceptor.class);
    @Autowired
    UserService userService;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("path:{}",request.getRequestURI());
        logger.info("requestParam:{}", MapUtil.mapToString(request.getParameterMap()));
        return true;
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

}
