package com.payexpress.trade.dohttp;


import com.payexpress.trade.http.HttpResultFunc;
import com.payexpress.trade.http.RetrofitSrevice;

import rx.Observable;
import rx.Subscriber;

public class AlipayPreOrder extends HttpResultFunc {
    private Subscriber subscriber;
    private String version;
    private String channel;
    private String subject;
    private String body;
    private String trans_amount;
    private String access_token;

    public AlipayPreOrder(Subscriber subscriber, String version, String channel, String subject,
                          String body, String trans_amount, String access_token){
        this.subscriber = subscriber;
        this.version = version;
        this.channel = channel;
        this.subject = subject;
        this.body = body;
        this.trans_amount = trans_amount;
        this.access_token = access_token;
    }

    @Override
    public Observable getObservable(RetrofitSrevice retrofitSrevice) {
        return retrofitSrevice.alipayPreOrder(version, channel, subject, body,
                trans_amount, access_token);
    }

    @Override
    public Subscriber getSubscriber() {
        return subscriber;
    }
}
