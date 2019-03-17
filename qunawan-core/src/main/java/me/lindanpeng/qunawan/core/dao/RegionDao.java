package me.lindanpeng.qunawan.core.dao;

import me.lindanpeng.qunawan.core.entity.Region;

import java.util.List;

public interface RegionDao {
     List<Region> getRegionsByParentId(int parentId);
}
