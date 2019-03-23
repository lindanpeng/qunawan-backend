package me.lindanpeng.qunawan.api.vo;

import me.lindanpeng.qunawan.core.entity.Evaluate;
import me.lindanpeng.qunawan.core.entity.Scenic;
import me.lindanpeng.qunawan.core.util.DateUtils;

public class EvaluateVo {
    private Long id;
    private Long scenicId;
    private Long userId;
    private String scenicName;
    private String avatar;
    private String nickname;
    private String content;
    private Integer score;
    private Integer beauty;
    private Integer excitement;
    private Integer ease;
    private Integer romantic;
    private Integer humanity;
    private String people;
    private String createTime;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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


    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public  static EvaluateVo fromEvaluateAndScenic(Evaluate evaluate, Scenic scenic){
        EvaluateVo evaluateVo=new EvaluateVo();
        evaluateVo.setId(evaluate.getId());
        evaluateVo.setUserId(evaluate.getUserId());
        evaluateVo.setAvatar(evaluate.getAvatar());
        evaluateVo.setNickname(evaluate.getNickname());
        evaluateVo.setContent(evaluate.getContent());
        evaluateVo.setCreateTime(DateUtils.dateToDateTime(evaluate.getCreateTime()));
        evaluateVo.setEase(evaluate.getEase());
        evaluateVo.setBeauty(evaluate.getBeauty());
        evaluateVo.setRomantic(evaluate.getRomantic());
        evaluateVo.setExcitement(evaluate.getExcitement());
        evaluateVo.setHumanity(evaluate.getHumanity());
        evaluateVo.setScenicId(evaluate.getScenicId());
        evaluateVo.setScore(evaluate.getScore());
        evaluateVo.setScenicName(scenic.getName());
        evaluateVo.setPeople(evaluate.getPeople());
        return evaluateVo;
    }
}
