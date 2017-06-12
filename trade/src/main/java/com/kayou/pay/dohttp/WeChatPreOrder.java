package com.kayou.pay.dohttp;


import com.kayou.pay.http.HttpResultFunc;
import com.kayou.pay.http.RetrofitSrevice;

import rx.Observable;
import rx.Subscriber;

public class WeChatPreOrder extends HttpResultFunc {
    private Subscriber subscriber;
    private String body;

    public WeChatPreOrder(Subscriber subscriber, String body){
        this.subscriber = subscriber;
        this.body = body;
    }

    @Override
    public Observable getObservable(RetrofitSrevice retrofitSrevice) {
        return retrofitSrevice.weChatPreOrder(body);
    }

    @Override
    public Subscriber getSubscriber() {
        return subscriber;
    }
}
