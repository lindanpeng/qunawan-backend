package me.lindanpeng.qunawan.web.controller;

import me.lindanpeng.qunawan.core.entity.User;
import me.lindanpeng.qunawan.web.dto.DoLoginDto;
import me.lindanpeng.qunawan.web.exception.ServiceException;
import me.lindanpeng.qunawan.web.protocol.ApiResponse;
import me.lindanpeng.qunawan.web.protocol.CodeMsg;
import me.lindanpeng.qunawan.web.service.SessionService;
import me.lindanpeng.qunawan.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;
    @Autowired
    SessionService sessionService;

    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "doLogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ApiResponse doLogin(@RequestBody DoLoginDto doLoginDto, HttpSession session) {
        logger.info("requestBody:{}", doLoginDto);
        if (StringUtils.isEmpty(doLoginDto.getEmail()) || StringUtils.isEmpty(doLoginDto.getPassword())) {
            logger.warn("参数错误");
            return ApiResponse.ERROR(CodeMsg.PARAMETER_ERROR);
        }
        User user = userService.login(doLoginDto.getEmail(), doLoginDto.getPassword());
        session.setAttribute("loginedUser", sessionService.getSessionData(user));
        return ApiResponse.SUCCESS("");
    }
//    @ResponseBody
//    @RequestMapping("/test")
//    public ApiResponse test(){
//        throw new ServiceException(CodeMsg.SYSTEM_ERROR);
//        //return "hello";
//    }
}
