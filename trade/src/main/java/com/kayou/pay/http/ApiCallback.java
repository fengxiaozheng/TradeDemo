package com.kayou.pay.http;



public interface ApiCallback<T extends ResponseResult> {

    void onSuccess(T res);

    void onFailure(int code, String msg);

    void onCompleted();

}
