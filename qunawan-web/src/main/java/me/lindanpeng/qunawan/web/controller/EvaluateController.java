package me.lindanpeng.qunawan.web.controller;

import me.lindanpeng.qunawan.core.dao.EvaluateDao;
import me.lindanpeng.qunawan.core.entity.Evaluate;
import me.lindanpeng.qunawan.core.util.PageHelper;
import me.lindanpeng.qunawan.web.protocol.ApiResponse;
import me.lindanpeng.qunawan.web.protocol.CodeMsg;
import me.lindanpeng.qunawan.web.service.EvaluateService;
import me.lindanpeng.qunawan.web.vo.EvaluateVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EvaluateController {
    private static final Logger logger= LoggerFactory.getLogger(EvaluateController.class);
    @Autowired
    EvaluateService evaluateService;
    @RequestMapping(value = "/evaluates",method = RequestMethod.GET)
    public ApiResponse<PageHelper.PageResult<EvaluateVo>> newEvaluates(Integer currentPage, Integer pageSize,Long scenicId){
        if (pageSize==null||pageSize>PageHelper.MAX_PAGE_SIZE)
            pageSize=PageHelper.DEFAULT_PAGE_SIZE;
        if (currentPage==null||currentPage<=0)
            currentPage=1;
        PageHelper.PageResult<EvaluateVo> data=evaluateService.getEvaluates(scenicId,currentPage,pageSize);
        return ApiResponse.SUCCESS(data);
    }
    @RequestMapping(value = "/userEvaluates")
    public ApiResponse<List<EvaluateVo>> userEvaluates(Long userId){
        if (userId==null)
            return ApiResponse.ERROR(CodeMsg.PARAMETER_ERROR);
        List<EvaluateVo> evaluateVos=evaluateService.userEvaluates(userId);
        return ApiResponse.SUCCESS(evaluateVos);

    }
//    @RequestMapping(value = "scenicEvaluates",method = RequestMethod.GET)
//    public ApiResponse<PageHelper.PageResult<EvaluateVo>> scenicEvaluates(Long scenicId,Integer currentPage){
//        if (scenicId==null){
//            logger.info("wrong scenicId,scenicId={}",scenicId);
//            return ApiResponse.ERROR(CodeMsg.PARAMETER_ERROR);
//        }
//        if (currentPage==null||currentPage<=0)
//            currentPage=1;
//        PageHelper.PageResult<EvaluateVo> pageResult=evaluateService.scenicEvaluates(scenicId,currentPage,PageHelper.DEFAULT_PAGE_SIZE);
//        return ApiResponse.SUCCESS(pageResult);
//    }
}
