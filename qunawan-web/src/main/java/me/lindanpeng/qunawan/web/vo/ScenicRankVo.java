package me.lindanpeng.qunawan.web.vo;

import me.lindanpeng.qunawan.core.entity.Scenic;
import me.lindanpeng.qunawan.core.entity.ScenicIntro;
import me.lindanpeng.qunawan.core.util.DateUtils;

public class ScenicRankVo {
    private Long id;
    private String name;
    private String thumbnail;
    private Long price;
    private Integer provinceId;
    private Integer cityId;
    private String province;
    private String city;
    private Short type;
    private String location;
    private String score;
    private String beauty;
    private String excitement;
    private String ease;
    private String romantic;
    private String humanity;
    private Long evaluateCount;
    private Long likeCount;
    private String createTime;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getEvaluateCount() {
        return evaluateCount;
    }

    public void setEvaluateCount(Long evaluateCount) {
        this.evaluateCount = evaluateCount;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getBeauty() {
        return beauty;
    }

    public void setBeauty(String beauty) {
        this.beauty = beauty;
    }

    public String getExcitement() {
        return excitement;
    }

    public void setExcitement(String excitement) {
        this.excitement = excitement;
    }

    public String getEase() {
        return ease;
    }

    public void setEase(String ease) {
        this.ease = ease;
    }

    public String getRomantic() {
        return romantic;
    }

    public void setRomantic(String romantic) {
        this.romantic = romantic;
    }

    public String getHumanity() {
        return humanity;
    }

    public void setHumanity(String humanity) {
        this.humanity = humanity;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public static ScenicRankVo fromScenic(Scenic scenic, ScenicIntro scenicIntro){
        ScenicRankVo scenicRankVo = new ScenicRankVo();
        scenicRankVo.setId(scenic.getId());
        scenicRankVo.setBeauty(String.format("%.1f",(float)scenic.getBeauty()/scenic.getEvaluateCount()));
        scenicRankVo.setHumanity(String.format("%.1f",(float)scenic.getHumanity()/scenic.getEvaluateCount()));
        scenicRankVo.setExcitement(String.format("%.1f",(float)scenic.getExcitement()/scenic.getEvaluateCount()));
        scenicRankVo.setRomantic(String.format("%.1f",(float)scenic.getRomantic()/scenic.getEvaluateCount()));
        scenicRankVo.setEase(String.format("%.1f",(float)scenic.getEase()/scenic.getEvaluateCount()));
        scenicRankVo.setScore(String.format("%.1f",(float)scenic.getScore()/scenic.getEvaluateCount()));
        scenicRankVo.setCity(scenic.getCity());
        scenicRankVo.setPrice(scenic.getPrice());
        scenicRankVo.setCityId(scenic.getCityId());
        scenicRankVo.setProvince(scenic.getProvince());
        scenicRankVo.setProvinceId(scenic.getProvinceId());
        scenicRankVo.setCreateTime(DateUtils.dateToDateTime(scenic.getCreateTime()));
        scenicRankVo.setEvaluateCount(scenic.getEvaluateCount());
        scenicRankVo.setLikeCount(scenic.getLikeCount());
        scenicRankVo.setLocation(scenic.getLocation());
        scenicRankVo.setName(scenic.getName());
        scenicRankVo.setThumbnail(scenic.getThumbnail());
        String temp=scenicIntro.getDescription().replaceAll("(<p>)|(</p>)|(\\s+)|(<br/>)|(<strong>)|(</strong>)", "");
        scenicRankVo.setDescription(temp.substring(0, temp.length()>200?200:temp.length()));
        return scenicRankVo;
    }
}
