package me.lindanpeng.qunawan.recommend.mahout;

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
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class ScenicRecommender {
    private final static Logger logger = LoggerFactory.getLogger(ScenicRecommender.class);
    private final static int NEIGHBORHOOD_NUM = 5;
    private final static int RECOMMENDER_NUM = 2;

    public Map<Long, List<RecommendedItem>> getRecommendResult(List<ScoreData> scoreDatas) {
        try {
            Map<Long,List<RecommendedItem>> resultMap=new HashMap<>();

            FastByIDMap<LinkedList<Preference>> linkedListFastByIDMap = new FastByIDMap<>();
            for (ScoreData scoreData : scoreDatas) {
                Long uId = scoreData.getUserId();
                Long itemId = scoreData.getItemId();
                Float value = scoreData.getValue();
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
            org.apache.mahout.cf.taste.recommender.Recommender r = new GenericUserBasedRecommender(dataModel, neighbor, user);

            LongPrimitiveIterator iter = dataModel.getUserIDs();

            while (iter.hasNext()) {
                long uid = iter.nextLong();
                List<RecommendedItem> list = r.recommend(uid, RECOMMENDER_NUM);
                resultMap.put(uid,list);
            }
            return resultMap;

        } catch (TasteException e1) {
            e1.printStackTrace();
            logger.error(e1.getMessage());
            return null;
        }


    }
}