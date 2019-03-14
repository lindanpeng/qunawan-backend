package me.lindanpeng.qunawan.core.dao;

import me.lindanpeng.qunawan.core.entity.ScenicImg;

import java.util.List;

public interface ScenicImgDao {
    public List<ScenicImg> findByScenicId(long scenicId);
}
