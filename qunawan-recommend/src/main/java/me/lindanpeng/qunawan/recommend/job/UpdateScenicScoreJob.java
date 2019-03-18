package me.lindanpeng.qunawan.recommend.job;

import me.lindanpeng.qunawan.core.cache.CommonRedisClient;
import me.lindanpeng.qunawan.core.dao.EvaluateDao;
import me.lindanpeng.qunawan.core.dao.ScenicDao;
import me.lindanpeng.qunawan.core.entity.Evaluate;
import me.lindanpeng.qunawan.core.entity.Scenic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 增量更新景点评分数据
 */
@Component
public class UpdateScenicScoreJob extends AbstractJob {

    private final static Logger logger = LoggerFactory.getLogger(UpdateScenicScoreJob.class);
    @Autowired
    ScenicDao scenicDao;
    @Autowired
    EvaluateDao evaluateDao;
    @Autowired
    CommonRedisClient commonRedisClient;
    private final String CACHE_KEY = "LastUpdateScenicScoreTime";
    private final int LIMIT_SIZE = 1000;
    //增量更新景点评分
   // @Scheduled(fixedDelay = 60 * 1000)
    public void updateScenicScoreIncrementally() throws ParseException {
        logger.info("[updateScenicScoreJob] start...");
        Date now = new Date();
        Date startTime = (Date) commonRedisClient.get(CACHE_KEY);
        if (startTime == null)
            startTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1970-01-01 00:00:00");
        int fetchSize=0;
        int start = 0;

        do {
            Map<Long, Map<String, Integer>> scoreMap = new HashMap<>();
            Map<Long, Long> countMap = new HashMap<>();
            List<Evaluate> evaluates = evaluateDao.getNewEvaluatesInTime(startTime, now, start, LIMIT_SIZE);
            fetchSize = evaluates.size();
            logger.info("[updateScenicScoreJob] start={},fetchSize={}",start,fetchSize);
            start = fetchSize + start;
            for (Evaluate e : evaluates) {
                Map<String, Integer> map = scoreMap.getOrDefault(e.getScenicId(), new HashMap<>());
                map.put("ease", map.getOrDefault("ease", 0) + e.getEase());
                map.put("beauty", map.getOrDefault("beauty", 0) + e.getBeauty());
                map.put("excitement", map.getOrDefault("excitement", 0) + e.getExcitement());
                map.put("humanity", map.getOrDefault("humanity", 0) + e.getHumanity());
                map.put("romantic", map.getOrDefault("romantic", 0) + e.getRomantic());
                map.put("score", map.getOrDefault("score", 0) + e.getScore());
                scoreMap.put(e.getScenicId(), map);

                countMap.put(e.getScenicId(), countMap.getOrDefault(e.getScenicId(), 0L) + 1);
            }
            logger.info("scoreMap size={}",scoreMap.size());
            for (Map.Entry<Long, Map<String, Integer>> e : scoreMap.entrySet()) {
                long id = e.getKey();
                Scenic scenic = scenicDao.findById(id);
                Long newEvaluateCount = scenic.getEvaluateCount() + countMap.get(e.getKey());
                Long newScore = (scenic.getScore() + e.getValue().get("score"));
                Long newBeauty = (scenic.getScore() + e.getValue().get("beauty"));
                Long newExcitement = (scenic.getExcitement() + e.getValue().get("excitement"));
                Long newHumanity = (scenic.getHumanity() + e.getValue().get("humanity"));
                Long newRomantic = (scenic.getRomantic() + e.getValue().get("romantic"));
                Long newEase = scenic.getEase() + e.getValue().get("ease");
                scenic.setBeauty(newBeauty);
                scenic.setScore(newScore);
                scenic.setEase(newEase);
                scenic.setHumanity(newHumanity);
                scenic.setRomantic(newRomantic);
                scenic.setExcitement(newExcitement);
                scenic.setEvaluateCount(newEvaluateCount);
                scenicDao.updateScenic(scenic);
            }
        } while (fetchSize >= LIMIT_SIZE);
        commonRedisClient.set(CACHE_KEY, now);
        logger.info("[updateScenicScoreJob] next start time:{}",now);
        logger.info("[updateScenicScoreJob] end...");
    }

}
