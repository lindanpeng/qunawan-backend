package me.lindanpeng.qunawan.web.controller;

import me.lindanpeng.qunawan.core.entity.Evaluate;
import me.lindanpeng.qunawan.core.util.PageHelper;
import me.lindanpeng.qunawan.web.protocol.ApiResponse;
import me.lindanpeng.qunawan.web.protocol.CodeMsg;
import me.lindanpeng.qunawan.web.service.EvaluateService;
import me.lindanpeng.qunawan.web.vo.EvaluateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvaluateController {
    @Autowired
    EvaluateService evaluateService;
    @RequestMapping(value = "/newEvaluates",method = RequestMethod.GET)
    public ApiResponse<PageHelper.PageResult<EvaluateVo>> newEvaluates(Integer currentPage, Integer pageSize){
        if (currentPage==null||pageSize==null)
            return ApiResponse.ERROR(CodeMsg.PARAMETER_ERROR);
        if (pageSize> PageHelper.MAX_PAGE_SIZE)
            return ApiResponse.ERROR(CodeMsg.PARAMETER_ERROR);
        PageHelper.PageResult<EvaluateVo> data=evaluateService.listNewEvaluate(currentPage,pageSize);
        return ApiResponse.SUCCESS(data);
    }
}
