package me.lindanpeng.qunawan.core.dao;

import me.lindanpeng.qunawan.core.entity.Evaluate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public interface EvaluateDao {
    List<Evaluate> getNewEvaluatesInTime(String startTime, String endTime, int start, int size);
    List<Evaluate> getEvaluates(Long scenicId, int start, int size);
    int countAll();
    int countByCondition(@Param("scenicId") Long scenicId);
    List<Evaluate> getEvaluatesByUserId(Long userId);
    List<Evaluate> getAll();
}
