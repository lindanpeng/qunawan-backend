package me.lindanpeng.qunawan.core.entity;

import java.util.Date;

public class Evaluate {
    private Long id;
    private Long scenicId;
    private Long userId;
    private String avatar;
    private String nickName;
    private String content;
    private Integer score;
    private Integer beauty;
    private Integer excitement;
    private Integer ease;
    private Integer romantic;
    private Integer humanity;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScenicId() {
        return scenicId;
    }

    public void setScenicId(Long scenicId) {
        this.scenicId = scenicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getBeauty() {
        return beauty;
    }

    public void setBeauty(Integer beauty) {
        this.beauty = beauty;
    }

    public Integer getExcitement() {
        return excitement;
    }

    public void setExcitement(Integer excitement) {
        this.excitement = excitement;
    }

    public Integer getEase() {
        return ease;
    }

    public void setEase(Integer ease) {
        this.ease = ease;
    }

    public Integer getRomantic() {
        return romantic;
    }

    public void setRomantic(Integer romantic) {
        this.romantic = romantic;
    }

    public Integer getHumanity() {
        return humanity;
    }

    public void setHumanity(Integer humanity) {
        this.humanity = humanity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Evaluate{" +
                "id=" + id +
                ", scenicId=" + scenicId +
                ", userId=" + userId +
                ", avatar='" + avatar + '\'' +
                ", nickName='" + nickName + '\'' +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", beauty=" + beauty +
                ", excitement=" + excitement +
                ", ease=" + ease +
                ", romantic=" + romantic +
                ", humanity=" + humanity +
                ", createTime=" + createTime +
                '}';
    }
}
