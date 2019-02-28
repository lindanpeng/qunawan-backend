package me.lindanpeng.qunawan.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    Logger logger= LoggerFactory.getLogger(IndexController.class);
    @RequestMapping("/index")
    public String index(HttpSession session){
        return "index";
    }

}
