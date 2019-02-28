package me.lindanpeng.qunawan.web.service;

import me.lindanpeng.qunawan.core.entity.User;
import me.lindanpeng.qunawan.web.dto.SessionData;
import org.springframework.stereotype.Service;

@Service
public class SessionService extends AbstractService {

    public SessionData getSessionData(User user){
        SessionData sessionData=new SessionData();
        sessionData.setEmail(user.getEmail());
        return sessionData;
    }
}
