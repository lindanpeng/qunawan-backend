package me.lindanpeng.qunawan.recommend.job;

import me.lindanpeng.qunawan.core.cache.CommonRedisClient;
import me.lindanpeng.qunawan.core.dao.EvaluateDao;
import me.lindanpeng.qunawan.core.entity.Evaluate;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
@Component
public class UserCFJob extends AbstractJob {
    private final static Logger logger = LoggerFactory.getLogger(UserCFJob.class);
    @Autowired
    EvaluateDao evaluateDao;
    @Autowired
    CommonRedisClient commonRedisClient;
    private final static int NEIGHBORHOOD_NUM = 5;
    private final static int RECOMMENDER_NUM = 10;
    @Scheduled(fixedDelay = 1000*60*60*24)
    public void userCF() throws TasteException {
        logger.info("[UserCFJob] start...");
        List<Evaluate> evaluates=evaluateDao.getALl();
        FastByIDMap<LinkedList<Preference>> linkedListFastByIDMap = new FastByIDMap<>();
        for (Evaluate evaluate:evaluates) {
            Long uId = evaluate.getUserId();
            Long itemId = evaluate.getScenicId();
            Float value = (float)evaluate.getScore();
            //创建该用户下的item list
            LinkedList<Preference> preferences = linkedListFastByIDMap.get(uId);
            if (preferences == null) preferences = new LinkedList<>();
            //构建偏好
            GenericPreference genericPreference = new GenericPreference(uId, itemId, value);
            preferences.add(genericPreference);
            //重新写入map集合
            linkedListFastByIDMap.put(uId, preferences);
        }
        //遍历map集合
        Set<Map.Entry<Long, LinkedList<Preference>>> entries = linkedListFastByIDMap.entrySet();
        // 构建偏好集合
        FastByIDMap<PreferenceArray> arrayFastByIDMap = new FastByIDMap<>();
        for (Map.Entry<Long, LinkedList<Preference>> entry : entries) {
            Long key = entry.getKey();
            LinkedList<Preference> value = entry.getValue();
            arrayFastByIDMap.put(key, new GenericUserPreferenceArray(value));
        }
        //最后 构建数据模型
        DataModel dataModel = new GenericDataModel(arrayFastByIDMap);
        UserSimilarity user = new EuclideanDistanceSimilarity(dataModel);
        NearestNUserNeighborhood neighbor = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, user, dataModel);
        Recommender r = new GenericUserBasedRecommender(dataModel, neighbor, user);
        LongPrimitiveIterator iter = dataModel.getUserIDs();

        while (iter.hasNext()) {
            long uid = iter.nextLong();
            List<RecommendedItem> list = r.recommend(uid, RECOMMENDER_NUM);
            System.out.printf("uid:%s", uid);
            for (RecommendedItem ritem : list) {
                System.out.printf("(%s,%f)", ritem.getItemID(), ritem.getValue());
            }
            System.out.println();
        }
    }
}
