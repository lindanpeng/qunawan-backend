package me.lindanpeng.qunawan.api.vo;

import me.lindanpeng.qunawan.core.entity.User;
import me.lindanpeng.qunawan.core.entity.dict.UserDict;
import me.lindanpeng.qunawan.core.util.DateUtils;

public class UserInfoVo {
    private Long userId;
    private String avatar;
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
    public String getAvatar() {
        return avatar;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public static UserInfoVo fromUser(User user){
        UserInfoVo userInfoVo=new UserInfoVo();
        userInfoVo.setNickname(user.getNickname());
        userInfoVo.setAvatar(user.getAvatar());
        userInfoVo.setGender(UserDict.GENDER_MAP.get(user.getGender()));
        userInfoVo.setIdentity(UserDict.IDENTITY_MAP.get(user.getIdentity()));
        userInfoVo.setBirthday(DateUtils.dateToDateTime(user.getBirthday()));
        userInfoVo.setUserId(user.getId());
        userInfoVo.setLocation(user.getProvince()+user.getCity());
        return userInfoVo;
    }



}
