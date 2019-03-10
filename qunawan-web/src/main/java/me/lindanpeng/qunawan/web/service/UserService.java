package me.lindanpeng.qunawan.web.service;

import me.lindanpeng.qunawan.core.entity.User;
import me.lindanpeng.qunawan.web.exception.ServiceException;
import me.lindanpeng.qunawan.web.protocol.CodeMsg;
import me.lindanpeng.qunawan.web.vo.UserInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService{
    private final Logger logger= LoggerFactory.getLogger(UserService.class);

   public UserInfoVo getUserInfo(Long userId){
        User user=userDao.findById(userId);
        UserInfoVo userInfoVo=UserInfoVo.fromUser(user);
        return userInfoVo;
   }


}
