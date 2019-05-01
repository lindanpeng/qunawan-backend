package me.lindanpeng.qunawan.core.dao;

import me.lindanpeng.qunawan.core.entity.ViewHistory;

import java.util.List;

public interface ViewHistoryDao {
     void add(ViewHistory viewHistory);
     List<ViewHistory> selectGroupByUserId();
}
