package me.lindanpeng.qunawan.web.controller;

import me.lindanpeng.qunawan.core.util.PageHelper;
import me.lindanpeng.qunawan.web.protocol.ApiResponse;
import me.lindanpeng.qunawan.web.protocol.CodeMsg;
import me.lindanpeng.qunawan.web.service.ScenicService;
import me.lindanpeng.qunawan.web.service.UserService;
import me.lindanpeng.qunawan.web.vo.ScenicDetailVo;
import me.lindanpeng.qunawan.web.vo.ScenicPreviewVo;
import me.lindanpeng.qunawan.web.vo.ScenicRankVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ScenicController {
    private static final Logger logger = LoggerFactory.getLogger(ScenicController.class);
    @Autowired
    ScenicService scenicService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/data/scenicPreview", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ApiResponse<PageHelper.PageResult<ScenicPreviewVo>> scenicRankPreview(Integer provinceId, Integer cityId, Integer currentPage) {

        if (currentPage == null || currentPage <= 0) {
            currentPage=1;
        }
        PageHelper.PageResult<ScenicPreviewVo> pageResult = scenicService.listHotScenicPreview(provinceId, cityId, currentPage,8);
        return ApiResponse.SUCCESS(pageResult);

    }
    @RequestMapping(value = "/data/scenicRank")
    public ApiResponse<PageHelper.PageResult<ScenicRankVo>> scenicRank(@RequestBody(required = false) Map<String,Object> params){
        if (params==null)
            params=new HashMap<>();
        Integer currentPage=(Integer) params.get("currentPage");
        Integer provinceId=(Integer) params.get("provinceId");
        Integer cityId=(Integer) params.get("cityId");
        String keyWord=(String) params.get("keyWord");
        if (currentPage == null || currentPage <= 0) {
            currentPage=1;
        }
        PageHelper.PageResult<ScenicRankVo> pageResult = scenicService.listHotScenicRank(keyWord,provinceId, cityId, currentPage,PageHelper.DEFAULT_PAGE_SIZE);
        return ApiResponse.SUCCESS(pageResult);
    }
    @RequestMapping(value = "/data/scenicDetail",method = RequestMethod.GET)
    public ApiResponse<ScenicDetailVo> scenicDetail(Long scenicId){
        if (scenicId==null){
            logger.info("wrong scenicId");
            return ApiResponse.ERROR(CodeMsg.PARAMETER_ERROR);
        }
        ScenicDetailVo scenicDetailVo=scenicService.getScenicDetail(scenicId);
        return ApiResponse.SUCCESS(scenicDetailVo);
    }
    @RequestMapping(value = "/data/scenicRecommend")
    public ApiResponse<PageHelper.PageResult<ScenicRankVo>> scenicRecommend(@RequestBody(required = false) Map<String,Object> params){

        return null;
    }
//    @RequestMapping(value = "/newScenics",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
//    public ApiResponse<PageHelper.PageResult<ScenicPreviewVo>> newestScenics(Integer provinceId, Integer cityId, Integer currentPage, Integer pageSize){
//        if (pageSize==null||pageSize>50){
//            return ApiResponse.ERROR(CodeMsg.PARAMETER_ERROR);
//        }
//        if (currentPage!=null&&currentPage<=0){
//            return ApiResponse.ERROR(CodeMsg.PARAMETER_ERROR);
//        }
//        if (currentPage==null){
//            currentPage=1;
//        }
//        PageHelper.PageResult<ScenicPreviewVo> pageResult=scenicService.listNewScenicPreview(provinceId,cityId,currentPage,pageSize);
//        return ApiResponse.SUCCESS(pageResult);
//    }

//    @RequestMapping(value = "test")
//    public ApiResponse<Scenic> test() {
//        // System.out.println(userService.getUserInfo(1L).getNickname());
//        return ApiResponse.SUCCESS(scenicService.getScenic(2));
//    }

}
