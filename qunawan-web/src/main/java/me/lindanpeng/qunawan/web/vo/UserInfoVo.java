package me.lindanpeng.qunawan.web.vo;

import me.lindanpeng.qunawan.core.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserInfoVo {
    private String nickname;
    private String gender;
    private String location;
    private String birthday;
    private String identity;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
    public static UserInfoVo fromUser(User user){
        UserInfoVo userInfoVo=new UserInfoVo();
        userInfoVo.setNickname(user.getNickname());
        //userInfoVo.setGender(GENDER_MAP.get(user.getGender()));
        //userInfoVo.setIdentity();
        return userInfoVo;
    }
//    private static final Map<Integer,String> GENDER_MAP=new HashMap<>();
//    private static final Map<Integer,String> IDENTITY_MAP=new HashMap<>();
//    static {
//        GENDER_MAP.put(0,"男");
//        GENDER_MAP.put(1,"女");
//        GENDER_MAP.put(2,"未知");
//
//    }
}
