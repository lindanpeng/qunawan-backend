package me.lindanpeng.qunawan.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RouterController {
    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/home")
    public String toHome(){
        return "person";
    }
    @RequestMapping("/view/scenicRank")
    public String toScenicRank(){
        return "scenic_rank";
    }
    @RequestMapping("view/scenicDetail")
    public String toScenicDetail(){
        return "scenic_detail";
    }
    @RequestMapping("/view/evaluates")
    public String toEvaluates(){
        return "evaluate";
    }
}
