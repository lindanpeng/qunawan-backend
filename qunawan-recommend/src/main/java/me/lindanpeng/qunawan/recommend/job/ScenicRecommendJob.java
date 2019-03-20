package me.lindanpeng.qunawan.recommend.job;

import me.lindanpeng.qunawan.core.cache.CommonRedisClient;
import me.lindanpeng.qunawan.core.dao.EvaluateDao;
import me.lindanpeng.qunawan.core.entity.Evaluate;
import me.lindanpeng.qunawan.recommend.mahout.ScenicRecommender;
import me.lindanpeng.qunawan.recommend.mahout.ScoreData;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ScenicRecommendJob extends AbstractJob {
    private final static Logger logger = LoggerFactory.getLogger(ScenicRecommendJob.class);
    @Autowired
    EvaluateDao evaluateDao;
    @Autowired
    CommonRedisClient commonRedisClient;
    @Autowired
    ScenicRecommender scenicRecommender;
    private final static String CACHE_KEY="SCENIC_RECOMMEND";
     @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    public void scenicRecommend()  {
        logger.info("[ScenicRecommendJob] start...");
        List<Evaluate> evaluates = evaluateDao.getAll();
        List<ScoreData> scoreDatas=new ArrayList<>();
        for (Evaluate evaluate:evaluates){
            ScoreData scoreData=new ScoreData();
            scoreData.setUserId(evaluate.getUserId());
            scoreData.setItemId(evaluate.getScenicId());
            scoreData.setValue(evaluate.getScore());
            scoreDatas.add(scoreData);
        }
        Map<Long,List<RecommendedItem>> result=scenicRecommender.getRecommendResult(scoreDatas);
        for (Map.Entry<Long,List<RecommendedItem>> entry:result.entrySet()){
            List<Integer> scenicIds=new ArrayList<>(entry.getValue().size());
            entry.getValue().stream().forEach(e->scenicIds.add((int)e.getItemID()));
            for (Integer scenic_id:scenicIds){
                commonRedisClient.rightPush(CACHE_KEY+":"+entry.getKey(),scenic_id);
            }
        }
        logger.info("[ScenicRecommendJob] end...");
    }

}
