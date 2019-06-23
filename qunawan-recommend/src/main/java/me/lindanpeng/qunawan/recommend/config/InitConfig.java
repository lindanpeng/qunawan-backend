package me.lindanpeng.qunawan.recommend.config;

import me.lindanpeng.qunawan.core.cache.CommonRedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitConfig implements InitializingBean {
    private final static Logger logger = LoggerFactory.getLogger(InitConfig.class);
    @Autowired
    CommonRedisClient commonRedisClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("init config...");
        commonRedisClient.del("LastUpdateScenicScoreTime");
        commonRedisClient.del("LastPostScenicToEsTime");
        commonRedisClient.del("SCENIC_RECOMMEND*");
    }
}
