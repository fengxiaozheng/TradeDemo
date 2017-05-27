package com.payexpress.trade.dohttp;


import com.payexpress.trade.http.HttpResultFunc;
import com.payexpress.trade.http.RetrofitSrevice;

import rx.Observable;
import rx.Subscriber;

public class GetAccessToken extends HttpResultFunc {
    private Subscriber subscriber;
    private String proxy_code;
    private String proxy_secret;

    public GetAccessToken(Subscriber subscriber, String proxy_code, String proxy_secret){
        this.subscriber = subscriber;
        this.proxy_code = proxy_code;
        this.proxy_secret = proxy_secret;
    }


    @Override
    public Observable getObservable(RetrofitSrevice retrofitSrevice) {
        return retrofitSrevice.getAccessToken(proxy_code, proxy_secret);
    }

    @Override
    public Subscriber getSubscriber() {
        return subscriber;
    }
}
