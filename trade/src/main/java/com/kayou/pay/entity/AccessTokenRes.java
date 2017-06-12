package com.kayou.pay.entity;


import com.kayou.pay.http.ResponseResult;

public class AccessTokenRes extends ResponseResult {
    private String proxy_no;
    private String proxy_name;
    private String proxy_code;

    public String getProxy_no() {
        return proxy_no;
    }

    public void setProxy_no(String proxy_no) {
        this.proxy_no = proxy_no;
    }

    public String getProxy_name() {
        return proxy_name;
    }

    public void setProxy_name(String proxy_name) {
        this.proxy_name = proxy_name;
    }

    public String getProxy_code() {
        return proxy_code;
    }

    public void setProxy_code(String proxy_code) {
        this.proxy_code = proxy_code;
    }
}
