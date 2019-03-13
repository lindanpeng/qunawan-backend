package me.lindanpeng.qunawan.web.service;

import me.lindanpeng.qunawan.core.entity.Scenic;
import me.lindanpeng.qunawan.core.util.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenicService extends AbstractService {

 public Scenic getScenic(int scenicId){
     return scenicDao.findById(scenicId);
 }
 public PageHelper.PageResult<Scenic> listHotScenics(int provniceId, int cityId, int startPage, int pageSize ){
     PageHelper.PageQuery pageQuery = PageHelper.getPageQuery(startPage, pageSize);
     List<Scenic>  scenics=scenicDao.listOrderByScore(provniceId,cityId,pageQuery.getStart(),pageQuery.getLimit());
     PageHelper.PageResult<Scenic> pageResult=PageHelper.getPageResult(scenics);
     return pageResult;
 }
 public PageHelper.PageResult<Scenic> listNewestScenics(int provinceId,int cityId,int startPage,int pageSize){
     PageHelper.PageQuery pageQuery = PageHelper.getPageQuery(startPage, pageSize);
     List<Scenic>  scenics=scenicDao.listOrderByCreateTime(provinceId,cityId,pageQuery.getStart(),pageQuery.getLimit());
     PageHelper.PageResult<Scenic> pageResult=PageHelper.getPageResult(scenics);
     return pageResult;
 }

}
