package com.echarts.client.bean;

import java.io.Serializable;

public class Repository implements Serializable{
    private Integer resId;

    private Integer userId;

    private Integer resGoodsId;

    private Integer resType;

    private Integer resNumber;

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getResGoodsId() {
        return resGoodsId;
    }

    public void setResGoodsId(Integer resGoodsId) {
        this.resGoodsId = resGoodsId;
    }

    public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }

    public Integer getResNumber() {
        return resNumber;
    }

    public void setResNumber(Integer resNumber) {
        this.resNumber = resNumber;
    }
}