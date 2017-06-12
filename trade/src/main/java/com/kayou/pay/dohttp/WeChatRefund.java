package com.kayou.pay.dohttp;


import com.kayou.pay.http.HttpResultFunc;
import com.kayou.pay.http.RetrofitSrevice;

import rx.Observable;
import rx.Subscriber;

public class WeChatRefund extends HttpResultFunc {
    private Subscriber subscriber;
    private String body;

    public WeChatRefund(Subscriber subscriber, String body){
        this.subscriber = subscriber;
        this.body = body;
    }

    @Override
    public Observable getObservable(RetrofitSrevice retrofitSrevice) {
        return retrofitSrevice.weChatRefund(body);
    }

    @Override
    public Subscriber getSubscriber() {
        return subscriber;
    }
}
