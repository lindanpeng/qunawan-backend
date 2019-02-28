package me.lindanpeng.qunawan.web.service;

import me.lindanpeng.qunawan.core.entity.User;
import me.lindanpeng.qunawan.web.exception.ServiceException;
import me.lindanpeng.qunawan.web.protocol.CodeMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService{
    private final Logger logger= LoggerFactory.getLogger(UserService.class);
   public User login(String email,String password){
      User user=userDao.findByEmail(email);
      if (null==user){
            logger.warn("用户不存在");
            throw new ServiceException(CodeMsg.ID_OR_PASSWD_ERROR);
      }
      if (!password.equals(user.getPassword())){
          logger.warn("密码错误");
            throw new ServiceException(CodeMsg.ID_OR_PASSWD_ERROR);
      }
      return user;
   }
}
