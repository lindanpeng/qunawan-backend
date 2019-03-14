package me.lindanpeng.qunawan.web.vo;

import me.lindanpeng.qunawan.core.entity.Scenic;
import me.lindanpeng.qunawan.core.entity.ScenicIntro;
import me.lindanpeng.qunawan.core.util.DateUtils;

import java.util.Date;

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
    private Long score;
    private Long beauty;
    private Long excitement;
    private Long ease;
    private Long romantic;
    private Long humanity;
    private Long evaluateCount;
    private Long likeCount;
    private String createTime;
    private String desc;

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

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getBeauty() {
        return beauty;
    }

    public void setBeauty(Long beauty) {
        this.beauty = beauty;
    }

    public Long getExcitement() {
        return excitement;
    }

    public void setExcitement(Long excitement) {
        this.excitement = excitement;
    }

    public Long getEase() {
        return ease;
    }

    public void setEase(Long ease) {
        this.ease = ease;
    }

    public Long getRomantic() {
        return romantic;
    }

    public void setRomantic(Long romantic) {
        this.romantic = romantic;
    }

    public Long getHumanity() {
        return humanity;
    }

    public void setHumanity(Long humanity) {
        this.humanity = humanity;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public static ScenicRankVo fromScenic(Scenic scenic, ScenicIntro scenicIntro){
        ScenicRankVo scenicRankVo = new ScenicRankVo();
        scenicRankVo.setId(scenic.getId());
        scenicRankVo.setBeauty(scenic.getBeauty() / (scenic.getEvaluateCount() == 0 ? 1 : scenic.getEvaluateCount()));
        scenicRankVo.setHumanity(scenic.getHumanity() / (scenic.getEvaluateCount() == 0 ? 1 : scenic.getEvaluateCount()));
        scenicRankVo.setExcitement(scenic.getExcitement() / (scenic.getEvaluateCount() == 0 ? 1 : scenic.getEvaluateCount()));
        scenicRankVo.setRomantic(scenic.getRomantic() / (scenic.getEvaluateCount() == 0 ? 1 : scenic.getEvaluateCount()));
        scenicRankVo.setEase(scenic.getEase() / (scenic.getEvaluateCount() == 0 ? 1 : scenic.getEvaluateCount()));
        scenicRankVo.setScore(scenic.getScore() / (scenic.getEvaluateCount() == 0 ? 1 : scenic.getEvaluateCount()));
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
        scenicRankVo.setDesc(scenicIntro.getDescription().replaceAll("(<p>)|(</p>)|(\\s+)|<br/>|<strong>|</strong>", "").substring(0, 100));
        return scenicRankVo;
    }
}
