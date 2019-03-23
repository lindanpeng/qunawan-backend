package me.lindanpeng.qunawan.api.service;

import me.lindanpeng.qunawan.core.entity.Region;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegionService extends AbstractService {
    public List<Region> loadAllProvinces(){
        return regionDao.getRegionsByParentId(0);
    }
    public List<Region> loadCitiesByProvinceId(Integer provinceId){
        return regionDao.getRegionsByParentId(provinceId);
    }
}
