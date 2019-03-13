package me.lindanpeng.qunawan.core.dao;

import me.lindanpeng.qunawan.core.entity.Scenic;

import java.util.List;
public interface ScenicDao {
    Scenic findById(long id);
    void updateScenic(Scenic scenic);
    List<Scenic> listOrderByScore(int provinceId, int cityId, int start, int limit);
    List<Scenic> listOrderByCreateTime(int provinceId, int cityId, int start, int limit);
}
