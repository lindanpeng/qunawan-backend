package me.lindanpeng.qunawan.api.service;

import me.lindanpeng.qunawan.core.entity.ViewHistory;
import org.springframework.stereotype.Service;

@Service
public class ViewHistoryService extends AbstractService {
    public void addViewHistory(long userId,long scenicId,long duration){
        ViewHistory viewHistory=new ViewHistory();
        viewHistory.setUserId(userId);
        viewHistory.setScenicId(scenicId);
        viewHistory.setDuration(duration);
        viewHistoryDao.add(viewHistory);

    }
}
