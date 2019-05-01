package me.lindanpeng.qunawan.api.controller;

import me.lindanpeng.qunawan.api.dto.RegisterDto;
import me.lindanpeng.qunawan.api.protocol.ApiResponse;
import me.lindanpeng.qunawan.api.protocol.CodeMsg;
import me.lindanpeng.qunawan.api.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(ScenicController.class);
    @Autowired
    SessionService sessionService;

    @RequestMapping(value = "/doRegister")
    public ApiResponse doRegister(@RequestBody RegisterDto registerDto, HttpSession session) {
        if (StringUtils.isEmpty(registerDto.getEmail())
                || StringUtils.isEmpty(registerDto.getPassword())
                || StringUtils.isEmpty(registerDto.getConfirmPassword())) {
            logger.info("wrong parameters,registerDto={}", registerDto);
            return ApiResponse.ERROR(CodeMsg.PARAMETER_ERROR);
        }
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())){
            logger.info("wrong password,password={},confirmPassword={}",registerDto.getPassword(),registerDto.getConfirmPassword());
            return ApiResponse.ERROR(CodeMsg.PARAMETER_ERROR);
        }
        sessionService.registerAndLogin(registerDto, session);
        return ApiResponse.SUCCESS("");
    }
}
