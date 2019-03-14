package me.lindanpeng.qunawan.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HtmlPathController {
    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/scenicRank/view")
    public String toScenicRank(){
        return "scenic_rank";
    }
}
