package com.echarts.client.bean;

import java.io.Serializable;

public class Seed implements Serializable{
    private Integer seedId;

    private String seedName;

    private Integer seedPrice;

    private Integer seedHarvestNumber;

    private Integer seedHarvestTime;

    private Integer cropId;

    private String seedIntroduction;

    public Integer getSeedId() {
        return seedId;
    }

    public void setSeedId(Integer seedId) {
        this.seedId = seedId;
    }

    public String getSeedName() {
        return seedName;
    }

    public void setSeedName(String seedName) {
        this.seedName = seedName == null ? null : seedName.trim();
    }

    public Integer getSeedPrice() {
        return seedPrice;
    }

    public void setSeedPrice(Integer seedPrice) {
        this.seedPrice = seedPrice;
    }

    public Integer getSeedHarvestNumber() {
        return seedHarvestNumber;
    }

    public void setSeedHarvestNumber(Integer seedHarvestNumber) {
        this.seedHarvestNumber = seedHarvestNumber;
    }

    public Integer getSeedHarvestTime() {
        return seedHarvestTime;
    }

    public void setSeedHarvestTime(Integer seedHarvestTime) {
        this.seedHarvestTime = seedHarvestTime;
    }

    public Integer getCropId() {
        return cropId;
    }

    public void setCropId(Integer cropId) {
        this.cropId = cropId;
    }

    public String getSeedIntroduction() {
        return seedIntroduction;
    }

    public void setSeedIntroduction(String seedIntroduction) {
        this.seedIntroduction = seedIntroduction == null ? null : seedIntroduction.trim();
    }
}