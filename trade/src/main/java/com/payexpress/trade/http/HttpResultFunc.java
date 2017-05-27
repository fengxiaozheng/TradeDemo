package com.payexpress.trade.http;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;


public abstract class HttpResultFunc<T extends ResponseResult> implements Func1<T, T> {

    @Override
    public T call(T t) {
        if (!("0000".equals(t.getRet_code()))){
            throw new ResultErrorException(t.getRet_msg());
        }

        return  t;
    }

    public abstract Observable getObservable(RetrofitSrevice retrofitSrevice);

    public abstract Subscriber getSubscriber();
}
