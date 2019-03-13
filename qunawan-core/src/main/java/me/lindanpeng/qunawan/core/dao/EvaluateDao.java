package me.lindanpeng.qunawan.core.dao;

import me.lindanpeng.qunawan.core.entity.Evaluate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public interface EvaluateDao {
    @Select("select * from evaluate where create_time >#{startTime} and create_time <=#{endTime} order by create_time desc limit #{start},#{size}")
    List<Evaluate> listNewEvaluates(Date startTime,Date endTime, int start, int size);
}
