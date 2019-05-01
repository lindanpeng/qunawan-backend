package me.lindanpeng.qunawan.api.service;

import me.lindanpeng.qunawan.api.exception.ServiceException;
import me.lindanpeng.qunawan.api.protocol.ApiResponse;
import me.lindanpeng.qunawan.api.protocol.CodeMsg;
import me.lindanpeng.qunawan.core.constant.UserConstant;
import me.lindanpeng.qunawan.core.entity.User;
import me.lindanpeng.qunawan.api.vo.UserInfoVo;
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
