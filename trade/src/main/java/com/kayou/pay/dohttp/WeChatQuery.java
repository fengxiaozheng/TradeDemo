package com.kayou.pay.dohttp;


import com.kayou.pay.http.HttpResultFunc;
import com.kayou.pay.http.RetrofitSrevice;

import rx.Observable;
import rx.Subscriber;

public class WeChatQuery extends HttpResultFunc {
    private Subscriber subscriber;
    private String body;

    public WeChatQuery(Subscriber subscriber, String body){
        this.subscriber = subscriber;
        this.body = body;
    }

    @Override
    public Observable getObservable(RetrofitSrevice retrofitSrevice) {
        return retrofitSrevice.weChatQuery(body);
    }

    @Override
    public Subscriber getSubscriber() {
        return subscriber;
    }
}
