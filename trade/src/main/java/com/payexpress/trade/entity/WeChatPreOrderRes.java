package com.payexpress.trade.entity;


import com.payexpress.trade.http.ResponseResult;

public class WeChatPreOrderRes extends ResponseResult {
    private String sign;
    private String pay_trans_no;
    private String prepay_id;
    private String app_id;
    private String nonce_str;
    private String device_info;
    private String trade_type;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPay_trans_no() {
        return pay_trans_no;
    }

    public void setPay_trans_no(String pay_trans_no) {
        this.pay_trans_no = pay_trans_no;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
}
