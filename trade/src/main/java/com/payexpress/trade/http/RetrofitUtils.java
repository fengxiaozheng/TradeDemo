package com.payexpress.trade.http;

import com.payexpress.trade.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RetrofitUtils {
    public static final String BASE_URL = RetrofitSrevice.BASE_URL;
    private static final int DEFAULT_TIMEOUT = 10;
    private Retrofit retrofit;
    private RetrofitSrevice retrofitSrevice;
    private static RetrofitUtils INSTANCE;

    private RetrofitUtils(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
        }
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        retrofitSrevice = retrofit.create(RetrofitSrevice.class);
    }

    public static RetrofitUtils getInstance(){
        if (null == INSTANCE){
            synchronized (RetrofitUtils.class){
                if (null == INSTANCE){
                    INSTANCE = new RetrofitUtils();
                }
            }
        }
        return INSTANCE;
    }

    public void doHttp(HttpResultFunc httpResultFunc){
        httpResultFunc.getObservable(retrofitSrevice)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(httpResultFunc)
                .subscribe(httpResultFunc.getSubscriber());
    }
}
