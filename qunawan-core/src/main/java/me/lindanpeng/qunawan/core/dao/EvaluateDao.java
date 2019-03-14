package me.lindanpeng.qunawan.core.dao;

import me.lindanpeng.qunawan.core.entity.Evaluate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public interface EvaluateDao {
    List<Evaluate> getNewEvaluatesInTime(Date startTime, Date endTime, int start, int size);
    List<Evaluate> getNewEvaluates(int start, int size);
    int countAll();
}
