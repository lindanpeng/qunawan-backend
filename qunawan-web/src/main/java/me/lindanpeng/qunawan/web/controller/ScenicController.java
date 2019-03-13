package me.lindanpeng.qunawan.web.controller;

import me.lindanpeng.qunawan.core.entity.Scenic;
import me.lindanpeng.qunawan.core.util.PageHelper;
import me.lindanpeng.qunawan.web.protocol.ApiResponse;
import me.lindanpeng.qunawan.web.protocol.CodeMsg;
import me.lindanpeng.qunawan.web.service.ScenicService;
import me.lindanpeng.qunawan.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ScenicController {
    @Autowired
    ScenicService scenicService;
    @Autowired
    UserService userService;
    @RequestMapping(value = "hotScenics",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ApiResponse<PageHelper.PageResult<Scenic>> hotScenics(Integer provinceId, Integer cityId, Integer startPage, Integer pageSize){
        if (pageSize==null||pageSize>50){
            return ApiResponse.ERROR(CodeMsg.PARAMETER_ERROR);
        }
        if (startPage!=null&&startPage<=0){
            return ApiResponse.ERROR(CodeMsg.PARAMETER_ERROR);
        }
        if (startPage==null){
            startPage=1;
        }
        PageHelper.PageResult<Scenic> pageResult=scenicService.listHotScenics(provinceId,cityId,startPage,pageSize);
        return ApiResponse.SUCCESS(pageResult);

    }
    @RequestMapping(value = "newestScenics",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ApiResponse<PageHelper.PageResult<Scenic>> newestScenics(Integer provinceId, Integer cityId, Integer startPage, Integer pageSize){
        if (pageSize==null||pageSize>50){
            return ApiResponse.ERROR(CodeMsg.PARAMETER_ERROR);
        }
        if (startPage!=null&&startPage<=0){
            return ApiResponse.ERROR(CodeMsg.PARAMETER_ERROR);
        }
        if (startPage==null){
            startPage=1;
        }
        PageHelper.PageResult<Scenic> pageResult=scenicService.listNewestScenics(provinceId,cityId,startPage,pageSize);
        return ApiResponse.SUCCESS(pageResult);

    }
    @RequestMapping(value = "test")
    public ApiResponse<Scenic> test(){
        System.out.println(userService.getUserInfo(1L));
        return ApiResponse.SUCCESS(scenicService.getScenic(2));
    }

}
