package com.kayou.pay.dohttp;


import com.kayou.pay.http.HttpResultFunc;
import com.kayou.pay.http.RetrofitSrevice;

import rx.Observable;
import rx.Subscriber;

public class AlipayRefund extends HttpResultFunc {
    private Subscriber subscriber;
    private String body;

    public AlipayRefund(Subscriber subscriber, String body){
        this.subscriber = subscriber;
        this.body = body;
    }

    @Override
    public Observable getObservable(RetrofitSrevice retrofitSrevice) {
        return retrofitSrevice.alipayRefund(body);
    }

    @Override
    public Subscriber getSubscriber() {
        return subscriber;
    }
}
