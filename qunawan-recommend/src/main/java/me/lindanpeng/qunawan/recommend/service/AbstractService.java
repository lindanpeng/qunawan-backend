package me.lindanpeng.qunawan.recommend.service;

import me.lindanpeng.qunawan.core.dao.EvaluateDao;
import me.lindanpeng.qunawan.core.dao.ScenicDao;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractService {
    @Autowired
    ScenicDao scenicDao;
    @Autowired
    EvaluateDao evaluateDao;

}
