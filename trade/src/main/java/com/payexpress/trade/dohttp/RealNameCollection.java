package com.payexpress.trade.dohttp;


import com.payexpress.trade.http.HttpResultFunc;
import com.payexpress.trade.http.RetrofitSrevice;

import rx.Observable;
import rx.Subscriber;

public class RealNameCollection extends HttpResultFunc {
    private Subscriber subscriber;
    private String trans_amount;
    private String bind_id;
    private String access_token;

    public RealNameCollection(Subscriber subscriber, String trans_amount, String bind_id,
                              String access_token){
        this.subscriber = subscriber;
        this.trans_amount = trans_amount;
        this.bind_id = bind_id;
        this.access_token = access_token;
    }

    @Override
    public Observable getObservable(RetrofitSrevice retrofitSrevice) {
        return retrofitSrevice.realNameCollction(trans_amount, bind_id, access_token);
    }

    @Override
    public Subscriber getSubscriber() {
        return subscriber;
    }
}
