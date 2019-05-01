package me.lindanpeng.qunawan.api.controller;

import me.lindanpeng.qunawan.core.entity.User;
import me.lindanpeng.qunawan.api.dto.DoLoginDto;
import me.lindanpeng.qunawan.api.protocol.ApiResponse;
import me.lindanpeng.qunawan.api.protocol.CodeMsg;
import me.lindanpeng.qunawan.api.service.ScenicService;
import me.lindanpeng.qunawan.api.service.SessionService;
import me.lindanpeng.qunawan.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;
    @Autowired
    SessionService sessionService;
    @Autowired
    ScenicService scenicService;


    @RequestMapping(value = "doLogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ApiResponse<String> doLogin(@RequestBody DoLoginDto doLoginDto, HttpSession session) {
        if (StringUtils.isEmpty(doLoginDto.getEmail()) || StringUtils.isEmpty(doLoginDto.getPassword())) {
            logger.warn("参数错误");
            return ApiResponse.ERROR(CodeMsg.PARAMETER_ERROR);
        }
        User user = sessionService.login(doLoginDto.getEmail(), doLoginDto.getPassword(),session);
        return ApiResponse.SUCCESS("");
    }
    @RequestMapping(value = "logout")
    public ApiResponse logout(HttpSession session){
        sessionService.clearSessionData(session);
        return ApiResponse.SUCCESS("");
    }

}
