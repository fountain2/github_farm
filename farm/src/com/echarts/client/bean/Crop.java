package com.echarts.client.bean;

import java.io.Serializable;

public class Crop implements Serializable{
    private Integer cropId;

    private String cropName;

    private Integer cropPrice;

    private String cropIntroduction;

    public Integer getCropId() {
        return cropId;
    }

    public void setCropId(Integer cropId) {
        this.cropId = cropId;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName == null ? null : cropName.trim();
    }

    public Integer getCropPrice() {
        return cropPrice;
    }

    public void setCropPrice(Integer cropPrice) {
        this.cropPrice = cropPrice;
    }

    public String getCropIntroduction() {
        return cropIntroduction;
    }

    public void setCropIntroduction(String cropIntroduction) {
        this.cropIntroduction = cropIntroduction == null ? null : cropIntroduction.trim();
    }
}