package me.lindanpeng.qunawan.recommend.job;

import me.lindanpeng.qunawan.recommend.service.EvaluateService;
import me.lindanpeng.qunawan.recommend.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateScenicScoreJob extends AbstractJob{
    @Autowired
    ScenicService scenicService;
    @Autowired
    EvaluateService evaluateService;
    @Override
    public void execute() {

    }
}
