package me.lindanpeng.qunawan.web.service;

import me.lindanpeng.qunawan.core.dao.ScenicDao;
import me.lindanpeng.qunawan.core.entity.Scenic;
import me.lindanpeng.qunawan.core.entity.ScenicImg;
import me.lindanpeng.qunawan.core.entity.ScenicIntro;
import me.lindanpeng.qunawan.core.util.PageHelper;
import me.lindanpeng.qunawan.web.vo.ScenicDetailVo;
import me.lindanpeng.qunawan.web.vo.ScenicPreviewVo;
import me.lindanpeng.qunawan.web.vo.ScenicRankVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScenicService extends AbstractService {

 public Scenic getScenic(int scenicId){
     return scenicDao.findById(scenicId);
 }
 public PageHelper.PageResult<ScenicPreviewVo> listHotScenicPreview(Integer provinceId, Integer cityId, int currentPage){
     PageHelper.PageQuery pageQuery = PageHelper.getPageQuery(currentPage,8);
     List<Scenic>  scenics=scenicDao.listOrderByScore(provinceId,cityId,pageQuery.getStart(),pageQuery.getLimit());
     List<ScenicPreviewVo> scenicPreviewVos =new ArrayList<>(scenics.size());
     for (Scenic s:scenics){
         scenicPreviewVos.add(ScenicPreviewVo.fromScenic(s));
     }
     int count=scenicDao.countByCondition(provinceId,cityId);
     PageHelper.PageResult<ScenicPreviewVo> pageResult=PageHelper.getPageResult(scenicPreviewVos,count,8);
     return pageResult;
 }
// public PageHelper.PageResult<ScenicPreviewVo> listNewScenicPreview(Integer provinceId, Integer cityId, int currentPage){
//     PageHelper.PageQuery pageQuery = PageHelper.getPageQuery(currentPage, 8);
//     List<Scenic>  scenics=scenicDao.listOrderByCreateTime(provinceId,cityId,pageQuery.getStart(),pageQuery.getLimit());
//     List<ScenicPreviewVo> scenicPreviewVos =new ArrayList<>(scenics.size());
//     for (Scenic s:scenics){
//         scenicPreviewVos.add(ScenicPreviewVo.fromScenic(s));
//     }
//     int count=scenicDao.countByCondition(provinceId,cityId);
//     PageHelper.PageResult<ScenicPreviewVo> pageResult=PageHelper.getPageResult(scenicPreviewVos,count,8);
//     return pageResult;
// }
    public PageHelper.PageResult<ScenicRankVo> listHotScenicRank(String keyWord,Integer provinceId, Integer cityId, int currentPage,int pageSize){
        PageHelper.PageQuery pageQuery = PageHelper.getPageQuery(currentPage, pageSize);
        List<Scenic>  scenics=scenicDao.listOrderByScore(provinceId,cityId,pageQuery.getStart(),pageQuery.getLimit());
        List<ScenicRankVo> scenicRankVos =new ArrayList<>(scenics.size());
        for (Scenic scenic:scenics){
            ScenicIntro scenicIntro=scenicIntroDao.findByScenicId(scenic.getId());
            scenicRankVos.add(ScenicRankVo.fromScenic(scenic,scenicIntro));
        }
        int count=scenicDao.countByCondition(provinceId,cityId);
        PageHelper.PageResult<ScenicRankVo> pageResult=PageHelper.getPageResult(scenicRankVos,count);
        return pageResult;
    }
//    public PageHelper.PageResult<ScenicRankVo> listNewScenicRank(Integer provinceId, Integer cityId, int currentPage,int pageSize){
//        PageHelper.PageQuery pageQuery = PageHelper.getPageQuery(currentPage, pageSize);
//        List<Scenic>  scenics=scenicDao.listOrderByCreateTime(provinceId,cityId,pageQuery.getStart(),pageQuery.getLimit());
//        List<ScenicRankVo> scenicRankVos =new ArrayList<>(scenics.size());
//        for (Scenic scenic:scenics){
//            ScenicIntro scenicIntro=scenicIntroDao.findByScenicId(scenic.getId());
//            scenicRankVos.add(ScenicRankVo.fromScenic(scenic,scenicIntro));
//        }
//        int count=scenicDao.countByCondition(provinceId,cityId);
//        PageHelper.PageResult<ScenicRankVo> pageResult=PageHelper.getPageResult(scenicRankVos,count);
//        return pageResult;
//    }
    public ScenicDetailVo getScenicDetail(long scenicId){
        Scenic scenic=scenicDao.findById(scenicId);
        ScenicIntro scenicIntro=scenicIntroDao.findByScenicId(scenicId);
        List<ScenicImg> scenicImgs=scenicImgDao.findByScenicId(scenicId);
        ScenicDetailVo scenicDetailVo=ScenicDetailVo.fromScenicAndScenicInfoAndScenicImgs(scenic,scenicIntro,scenicImgs);
        return scenicDetailVo;
    }
}
