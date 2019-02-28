package me.lindanpeng.qunawan.web.service;

import me.lindanpeng.qunawan.core.entity.Region;
import me.lindanpeng.qunawan.core.entity.Scenic;
import me.lindanpeng.qunawan.web.service.ScenicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScenicServiceTest {
    @Autowired
    ScenicService scenicService;
    @Test
    public void TestFindById(){
     //   scenicService.test();
    }
}
