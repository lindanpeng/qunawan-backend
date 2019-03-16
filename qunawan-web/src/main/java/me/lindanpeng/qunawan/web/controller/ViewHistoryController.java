package me.lindanpeng.qunawan.web.controller;

import me.lindanpeng.qunawan.web.dto.SessionData;
import me.lindanpeng.qunawan.web.protocol.ApiResponse;
import me.lindanpeng.qunawan.web.protocol.CodeMsg;
import me.lindanpeng.qunawan.web.service.SessionService;
import me.lindanpeng.qunawan.web.service.ViewHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class ViewHistoryController {
    private static final Logger logger= LoggerFactory.getLogger(ViewHistoryController.class);
    @Autowired
    ViewHistoryService viewHistoryService;
    @Autowired
    SessionService sessionService;
    @RequestMapping(value = "userSkim",method = RequestMethod.POST)
    public ApiResponse userSkim(HttpSession session, @RequestBody Map<String,Long> params){
        Long scenicId=params.get("scenicId");
        Long duration=params.get("duration");
        if (scenicId==null||duration==null) {
            logger.info("wrong parameter,scenicId={},duration={}",scenicId,duration);
            return ApiResponse.ERROR(CodeMsg.PARAMETER_ERROR);
        }
        SessionData sessionData=sessionService.loadSessionData(session);
        viewHistoryService.addViewHistory(sessionData.getUserId(),scenicId,duration);
        return ApiResponse.SUCCESS("");
    }

}

