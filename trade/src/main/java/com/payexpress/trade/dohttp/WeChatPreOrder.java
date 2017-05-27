package com.payexpress.trade.dohttp;


import com.payexpress.trade.http.HttpResultFunc;
import com.payexpress.trade.http.RetrofitSrevice;

import rx.Observable;
import rx.Subscriber;

public class WeChatPreOrder extends HttpResultFunc {
    private Subscriber subscriber;
    private String version;
    private String channel;
    private String body;
    private String trans_amount;
    private String access_token;

    public WeChatPreOrder(Subscriber subscriber, String version, String channel,
                          String body, String trans_amount, String access_token){
        this.subscriber = subscriber;
        this.version = version;
        this.channel = channel;
        this.body = body;
        this.trans_amount = trans_amount;
        this.access_token = access_token;
    }

    @Override
    public Observable getObservable(RetrofitSrevice retrofitSrevice) {
        return retrofitSrevice.weChatPreOrder(version, channel,body, trans_amount,
                access_token);
    }

    @Override
    public Subscriber getSubscriber() {
        return subscriber;
    }
}
