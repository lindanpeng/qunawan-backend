package me.lindanpeng.qunawan.api.service;

import me.lindanpeng.qunawan.core.dao.*;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractService {
    @Autowired
    UserDao userDao;
    @Autowired
    ScenicDao scenicDao;
    @Autowired
    EvaluateDao evaluateDao;
    @Autowired
    ScenicIntroDao scenicIntroDao;
    @Autowired
    ScenicImgDao scenicImgDao;
    @Autowired
    ViewHistoryDao viewHistoryDao;
    @Autowired
    RegionDao regionDao;
}
