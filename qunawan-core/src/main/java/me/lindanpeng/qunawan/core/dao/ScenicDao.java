package me.lindanpeng.qunawan.core.dao;

import me.lindanpeng.qunawan.core.entity.Scenic;

import java.util.List;
public interface ScenicDao {
    Scenic findById(long id);
    void updateScenic(Scenic scenic);
    List<Scenic> listOrderByScore(Integer provinceId, Integer cityId, int start, int size);
    List<Scenic> listOrderByCreateTime(Integer provinceId, Integer cityId, int start, int size);
    int countByCondition(Integer provinceId,Integer cityId);
}
