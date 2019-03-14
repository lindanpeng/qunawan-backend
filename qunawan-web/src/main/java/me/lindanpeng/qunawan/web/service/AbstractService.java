package me.lindanpeng.qunawan.web.service;

import me.lindanpeng.qunawan.core.dao.*;
import me.lindanpeng.qunawan.core.entity.Evaluate;
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
}
