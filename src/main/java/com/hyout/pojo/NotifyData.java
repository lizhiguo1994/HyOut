package com.hyout.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/14.
 * {"result":"1000","payAmount":"1.00","merchantId":"102189","sign":"a5932e01c6db57ec360a1b969d05931a"
 * "successAmount":"1.00","transNo":"11175100011536905252","merchantOrderNo":"DKQY2018091414070001541928","version":"1.0"}
 */
public class NotifyData implements Serializable{
    String result;
    String payAmount;
    String merchantId;
    String sign;
    String successAmount;
    String transNo;
    String merchantOrderNo;
    String version;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSuccessAmount() {
        return successAmount;
    }

    public void setSuccessAmount(String successAmount) {
        this.successAmount = successAmount;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "result=" + result + "&" +
                "payAmount=" + payAmount + "&" +
                "merchantId=" + merchantId + "&" +
                "sign=" + sign + "&" +
                "successAmount=" + successAmount + "&" +
                "transNo=" + transNo + "&" +
                "merchantOrderNo=" + merchantOrderNo + "&" +
                "version=" + version ;
    }
}
