package me.lindanpeng.qunawan.web.service;

import com.mysql.cj.Session;
import me.lindanpeng.qunawan.core.entity.User;
import me.lindanpeng.qunawan.web.dto.SessionData;
import me.lindanpeng.qunawan.web.exception.ServiceException;
import me.lindanpeng.qunawan.web.protocol.CodeMsg;
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
    public SessionData loadSessionData(HttpSession session){
        SessionData sessionData= (SessionData) session.getAttribute(SESSION_KEY);
        return sessionData;
    }
    public void clearSessionData(HttpSession session){
        session.setAttribute(SESSION_KEY,null);
    }
}
