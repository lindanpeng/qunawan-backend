package me.lindanpeng.qunawan.web.controller;

import me.lindanpeng.qunawan.web.dto.SessionData;
import me.lindanpeng.qunawan.web.protocol.ApiResponse;
import me.lindanpeng.qunawan.web.service.SessionService;
import me.lindanpeng.qunawan.web.service.UserService;
import me.lindanpeng.qunawan.web.vo.UserInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    Logger logger= LoggerFactory.getLogger(UserController.class);
    @Autowired
    SessionService sessionService;
    @Autowired
    UserService userService;


    @RequestMapping(value = "userInfo",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ApiResponse<UserInfoVo> getUserInfo(HttpSession session){
        SessionData sessionData=sessionService.loadSessionData(session);
        Long userId=sessionData.getUserId();
        UserInfoVo userInfoVo=userService.getUserInfo(userId);
        return ApiResponse.SUCCESS(userInfoVo);
    }


}
