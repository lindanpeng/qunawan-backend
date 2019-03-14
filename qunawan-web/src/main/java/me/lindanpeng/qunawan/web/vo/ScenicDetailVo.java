package me.lindanpeng.qunawan.web.vo;

import me.lindanpeng.qunawan.core.entity.Scenic;
import me.lindanpeng.qunawan.core.entity.ScenicImg;
import me.lindanpeng.qunawan.core.entity.ScenicIntro;
import me.lindanpeng.qunawan.core.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class ScenicDetailVo {
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
    private String description;
    private List<String> imgUrls;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }
    public static  ScenicDetailVo fromScenicAndScenicInfoAndScenicImgs(Scenic scenic, ScenicIntro scenicIntro, List<ScenicImg> scenicImgs){
        ScenicDetailVo scenicDetailVo=new ScenicDetailVo();
        scenicDetailVo.setId(scenic.getId());
        scenicDetailVo.setBeauty(scenic.getBeauty()/(scenic.getEvaluateCount()==0?1:scenic.getEvaluateCount()));
        scenicDetailVo.setHumanity(scenic.getHumanity()/(scenic.getEvaluateCount()==0?1:scenic.getEvaluateCount()));
        scenicDetailVo.setExcitement(scenic.getExcitement()/(scenic.getEvaluateCount()==0?1:scenic.getEvaluateCount()));
        scenicDetailVo.setRomantic(scenic.getRomantic()/(scenic.getEvaluateCount()==0?1:scenic.getEvaluateCount()));
        scenicDetailVo.setEase(scenic.getEase()/(scenic.getEvaluateCount()==0?1:scenic.getEvaluateCount()));
        scenicDetailVo.setScore(scenic.getScore()/(scenic.getEvaluateCount()==0?1:scenic.getEvaluateCount()));
        scenicDetailVo.setCity(scenic.getCity());
        scenicDetailVo.setPrice(scenic.getPrice());
        scenicDetailVo.setCityId(scenic.getCityId());
        scenicDetailVo.setProvince(scenic.getProvince());
        scenicDetailVo.setProvinceId(scenic.getProvinceId());
        scenicDetailVo.setCreateTime(DateUtils.dateToDateTime(scenic.getCreateTime()));
        scenicDetailVo.setEvaluateCount(scenic.getEvaluateCount());
        scenicDetailVo.setLikeCount(scenic.getLikeCount());
        scenicDetailVo.setLocation(scenic.getLocation());
        scenicDetailVo.setName(scenic.getName());
        scenicDetailVo.setThumbnail(scenic.getThumbnail());
        scenicDetailVo.setDescription(scenicIntro.getDescription());
        if (scenicDetailVo.imgUrls==null)
            scenicDetailVo.imgUrls=new ArrayList<>(scenicImgs.size());
        for (ScenicImg scenicImg:scenicImgs){
            String imgUrl=scenicImg.getImgUrl();
            scenicDetailVo.imgUrls.add(imgUrl);
        }
        return scenicDetailVo;
    }
}
