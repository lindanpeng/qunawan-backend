package me.lindanpeng.qunawan.recommend.job;

import me.lindanpeng.qunawan.core.cache.CommonRedisClient;
import me.lindanpeng.qunawan.core.dao.ScenicDao;
import me.lindanpeng.qunawan.core.dao.ScenicIntroDao;
import me.lindanpeng.qunawan.core.entity.Scenic;
import me.lindanpeng.qunawan.core.entity.ScenicIntro;
import me.lindanpeng.qunawan.core.es.model.EsScenic;
import me.lindanpeng.qunawan.core.es.repository.EsScenicRepository;
import me.lindanpeng.qunawan.core.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class PostScenicToEsJob extends AbstractJob {
    private final static Logger logger= LoggerFactory.getLogger(PostScenicToEsJob.class);
    @Autowired
    EsScenicRepository esScenicRepository;
    @Autowired
    ScenicDao scenicDao;
    @Autowired
    ScenicIntroDao scenicIntroDao;
    @Autowired
    CommonRedisClient commonRedisClient;
     final int LIMIT_SIZE = 100;
     final String CACHE_KEY = "LastPostScenicToEsTime";

    //@Scheduled(fixedDelay = 60 * 1000)
    public void postScenicToEs() throws ParseException {
        logger.info("[PostScenicToEsJob] start...");
        Date now = new Date();
        Date startTime = (Date) commonRedisClient.get(CACHE_KEY);
        if (startTime == null)
            startTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1970-01-01 00:00:00");
        int fetchSize=0;
        int start = 0;
        do {
            List<Scenic> scenics=scenicDao.getScenicsInTime(DateUtils.dateToDateTime(startTime),DateUtils.dateToDateTime(now),start,LIMIT_SIZE);
            fetchSize=scenics.size();
            start=start+fetchSize;
            for (Scenic scenic:scenics){
                EsScenic esScenic=new EsScenic();
                ScenicIntro scenicIntro=scenicIntroDao.findByScenicId(scenic.getId());
                esScenic.setId(scenic.getId());
                esScenic.setAvgScore(scenic.getScore()/(float)scenic.getEvaluateCount());
                esScenic.setName(scenic.getName());
                esScenic.setCity(scenic.getCity());
                esScenic.setProvince(scenic.getProvince());
                esScenic.setLocation(scenic.getLocation());
                esScenic.setDescription(scenicIntro.getDescription());
                esScenic.setProvinceId(scenic.getProvinceId());
                esScenic.setCityId(scenic.getCityId());
                esScenicRepository.save(esScenic);
            }

        }while (fetchSize>=LIMIT_SIZE);
        commonRedisClient.set(CACHE_KEY, now);
        logger.info("[PostScenicToEsJob] next start time:{}",now);
        logger.info("[PostScenicToEsJob] end...");
    }
}
