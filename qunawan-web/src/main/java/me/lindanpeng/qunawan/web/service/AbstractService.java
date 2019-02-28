package me.lindanpeng.qunawan.web.service;

import me.lindanpeng.qunawan.core.dao.ScenicDao;
import me.lindanpeng.qunawan.core.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractService {
    @Autowired
    UserDao userDao;
    @Autowired
    ScenicDao scenicDao;
}
