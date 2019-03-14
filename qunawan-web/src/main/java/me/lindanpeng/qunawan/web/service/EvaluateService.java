package me.lindanpeng.qunawan.web.service;

import me.lindanpeng.qunawan.core.entity.Evaluate;
import me.lindanpeng.qunawan.core.entity.Scenic;
import me.lindanpeng.qunawan.core.util.PageHelper;
import me.lindanpeng.qunawan.web.vo.EvaluateVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluateService extends AbstractService{
    public PageHelper.PageResult<EvaluateVo> listNewEvaluate(int currentPage, int pageSize){
        PageHelper.PageQuery pageQuery=PageHelper.getPageQuery(currentPage,pageSize);
        List<Evaluate> list=evaluateDao.getNewEvaluates(pageQuery.getStart(),pageQuery.getLimit());
        List<EvaluateVo> evaluateVos=new ArrayList<>(list.size());
        for (Evaluate evaluate:list){
            Scenic scenic=scenicDao.findById(evaluate.getScenicId());
            EvaluateVo evaluateVo=EvaluateVo.fromEvaluateAndScenic(evaluate,scenic);
            evaluateVos.add(evaluateVo);
        }
        int count=evaluateDao.countAll();
        PageHelper.PageResult<EvaluateVo> res=PageHelper.getPageResult(evaluateVos,count,8);
        return res;
    }
}
