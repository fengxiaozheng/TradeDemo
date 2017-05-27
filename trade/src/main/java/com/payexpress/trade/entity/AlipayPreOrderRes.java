package com.payexpress.trade.entity;


import com.payexpress.trade.http.ResponseResult;

public class AlipayPreOrderRes extends ResponseResult {
    private String pay_trans_no;
    private String order_info;

    public String getPay_trans_no() {
        return pay_trans_no;
    }

    public void setPay_trans_no(String pay_trans_no) {
        this.pay_trans_no = pay_trans_no;
    }

    public String getOrder_info() {
        return order_info;
    }

    public void setOrder_info(String order_info) {
        this.order_info = order_info;
    }
}
