package me.lindanpeng.qunawan.web.vo;

import me.lindanpeng.qunawan.core.entity.Scenic;

public class ScenicPreviewVo {
    private Long id;
    private String name;
    private String thumbnail;
    private Long price;
    private Integer provinceId;
    private Integer cityId;
    private String province;
    private String city;
    private String score;

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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public static ScenicPreviewVo fromScenic(Scenic scenic){
        ScenicPreviewVo scenicPreviewVo =new ScenicPreviewVo();
        scenicPreviewVo.setId(scenic.getId());
        scenicPreviewVo.setCityId(scenic.getCityId());
        scenicPreviewVo.setProvinceId(scenic.getProvinceId());
        scenicPreviewVo.setProvince(scenic.getProvince());
        scenicPreviewVo.setCity(scenic.getCity());
        scenicPreviewVo.setName(scenic.getName());
        scenicPreviewVo.setPrice(scenic.getPrice());
        scenicPreviewVo.setThumbnail(scenic.getThumbnail());
        scenicPreviewVo.setScore(String.format("%.1f",(float)scenic.getScore()/scenic.getEvaluateCount()));
        return scenicPreviewVo;
    }
}
