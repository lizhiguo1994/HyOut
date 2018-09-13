package com.hyout.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Integer id;

    private Integer merchantId;

    private String merchantOrderNo;

    private BigDecimal orderAmount;

    private String payType;

    private Integer orderStatus;

    private Date orderTime;

    private Date updateTime;

    public Order(Integer id, Integer merchantId, String merchantOrderNo, BigDecimal orderAmount, String payType, Integer orderStatus, Date orderTime, Date updateTime) {
        this.id = id;
        this.merchantId = merchantId;
        this.merchantOrderNo = merchantOrderNo;
        this.orderAmount = orderAmount;
        this.payType = payType;
        this.orderStatus = orderStatus;
        this.orderTime = orderTime;
        this.updateTime = updateTime;
    }

    public Order() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo == null ? null : merchantOrderNo.trim();
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}