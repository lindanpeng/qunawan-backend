package me.lindanpeng.qunawan.api.service;

import me.lindanpeng.qunawan.api.dto.RegisterDto;
import me.lindanpeng.qunawan.core.constant.UserConstant;
import me.lindanpeng.qunawan.core.entity.User;
import me.lindanpeng.qunawan.api.dto.SessionData;
import me.lindanpeng.qunawan.api.exception.ServiceException;
import me.lindanpeng.qunawan.api.protocol.CodeMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionService extends AbstractService {
    private final static String SESSION_KEY="loginedUser";
    private final Logger logger= LoggerFactory.getLogger(SessionService.class);

    public SessionData wrapSessionData(User user){
        SessionData sessionData=new SessionData();
        sessionData.setEmail(user.getEmail());
        sessionData.setUserId(user.getId());
        return sessionData;
    }
    public User login(String email, String password, HttpSession session){
        User user=userDao.findByEmail(email);
        if (null==user){
            logger.warn("用户不存在");
            throw new ServiceException(CodeMsg.ID_OR_PASSWD_ERROR);
        }
        if (!password.equals(user.getPassword())){
            logger.warn("密码错误");
            throw new ServiceException(CodeMsg.ID_OR_PASSWD_ERROR);
        }
        session.setAttribute(SESSION_KEY, this.wrapSessionData(user));
        return user;
    }
    //注册功能
    public void register(String email,String password){
        User user=userDao.findByEmail(email);
        if (user!=null)
            throw new ServiceException(CodeMsg.USER_EXISTS);

        user=new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setAvatar(UserConstant.DEFAULT_AVATAR);
        user.setNickname(UserConstant.DEFAULT_NICKNAME);
        user.setGender(UserConstant.GENDEAR_UNKNOWN);
        userDao.add(user);
    }
    public void registerAndLogin(RegisterDto registerDto,HttpSession session){
        register(registerDto.getEmail(),registerDto.getPassword());
        login(registerDto.getEmail(),registerDto.getPassword(),session);
    }
    public SessionData loadSessionData(HttpSession session){
        SessionData sessionData= (SessionData) session.getAttribute(SESSION_KEY);
        return sessionData;
    }
    public void clearSessionData(HttpSession session){
        session.setAttribute(SESSION_KEY,null);
    }
}
