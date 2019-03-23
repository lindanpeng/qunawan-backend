package me.lindanpeng.qunawan.api.controller;

import me.lindanpeng.qunawan.api.dto.SessionData;
import me.lindanpeng.qunawan.api.protocol.ApiResponse;
import me.lindanpeng.qunawan.api.service.SessionService;
import me.lindanpeng.qunawan.api.service.UserService;
import me.lindanpeng.qunawan.api.vo.UserInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
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
