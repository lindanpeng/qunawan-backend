package me.lindanpeng.qunawan.recommend.job;

import me.lindanpeng.qunawan.core.cache.CommonRedisClient;
import me.lindanpeng.qunawan.core.dao.EvaluateDao;
import me.lindanpeng.qunawan.core.entity.Evaluate;
import me.lindanpeng.qunawan.recommend.mahout.ScenicRecommender;
import me.lindanpeng.qunawan.recommend.mahout.ScoreData;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Component
public class ScenicRecommendJob2 {
    private final static Logger logger = LoggerFactory.getLogger(ScenicRecommendJob.class);
    @Autowired
    EvaluateDao evaluateDao;
    @Autowired
    CommonRedisClient commonRedisClient;
    @Autowired
    ScenicRecommender scenicRecommender;
    private final static String CACHE_KEY="SCENIC_RECOMMEND_2";
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    public void scenicRecommend2()  {
        logger.info("[ScenicRecommendJob2] start...");

        logger.info("[ScenicRecommendJob] end...");
    }
}
