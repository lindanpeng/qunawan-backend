package me.lindanpeng.qunawan.web.controller;

import me.lindanpeng.qunawan.core.entity.Region;
import me.lindanpeng.qunawan.web.protocol.ApiResponse;
import me.lindanpeng.qunawan.web.protocol.CodeMsg;
import me.lindanpeng.qunawan.web.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RegionController {
    @Autowired
    RegionService regionService;
    @RequestMapping("/loadProvinces")
    public ApiResponse loadProvinces(){
        List<Region> regions=regionService.loadAllProvinces();
        return ApiResponse.SUCCESS(regions);
    }
    @RequestMapping("/loadCitiesByProvinceId")
    public ApiResponse loadCitiesByProvinceId(Integer provinceId){
        if (provinceId==null)
            return ApiResponse.ERROR(CodeMsg.PARAMETER_ERROR);
        List<Region> regions=regionService.loadCitiesByProvinceId(provinceId);
        return ApiResponse.SUCCESS(regions);
    }
}
