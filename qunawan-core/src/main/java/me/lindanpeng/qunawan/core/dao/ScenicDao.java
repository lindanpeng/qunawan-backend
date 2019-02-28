package me.lindanpeng.qunawan.core.dao;

import me.lindanpeng.qunawan.core.entity.Region;
import me.lindanpeng.qunawan.core.entity.Scenic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ScenicDao {
    @Select("select * from region where id =#{id}")
    public Region findbyId(int id);
}
