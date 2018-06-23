package com.echarts.client.bean;

import java.io.Serializable;
import java.util.Date;

public class Field implements Serializable{
    private Integer fieldId;

    private Integer userId;

    private Integer seedId;

    private Integer fieldStatus;

    private Integer filedHarvestNumber;

    private Date fieldPlantingTime;

    
    public Field() {
	}

	public Field(Integer fieldId, Integer userId, Integer seedId, Integer fieldStatus, Integer filedHarvestNumber,
			Date fieldPlantingTime) {
		this.fieldId = fieldId;
		this.userId = userId;
		this.seedId = seedId;
		this.fieldStatus = fieldStatus;
		this.filedHarvestNumber = filedHarvestNumber;
		this.fieldPlantingTime = fieldPlantingTime;
	}

	public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSeedId() {
        return seedId;
    }

    public void setSeedId(Integer seedId) {
        this.seedId = seedId;
    }

    public Integer getFieldStatus() {
        return fieldStatus;
    }

    public void setFieldStatus(Integer fieldStatus) {
        this.fieldStatus = fieldStatus;
    }

    public Integer getFiledHarvestNumber() {
        return filedHarvestNumber;
    }

    public void setFiledHarvestNumber(Integer filedHarvestNumber) {
        this.filedHarvestNumber = filedHarvestNumber;
    }

    public Date getFieldPlantingTime() {
        return fieldPlantingTime;
    }

    public void setFieldPlantingTime(Date fieldPlantingTime) {
        this.fieldPlantingTime = fieldPlantingTime;
    }
}