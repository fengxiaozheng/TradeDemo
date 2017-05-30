package com.payexpress.trade.http;


import com.payexpress.trade.entity.AccessTokenRes;
import com.payexpress.trade.entity.AlipayPreOrderRes;
import com.payexpress.trade.entity.RealNameCollectionRes;
import com.payexpress.trade.entity.WeChatPreOrderRes;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface RetrofitSrevice {
    public static final String BASE_URL = "http://192.168.201.92:8080/payexpress/";

    /**
     * 获取access_token
     *
     * @param proxy_code
     * @param proxy_secret
     * @return
     */
    @FormUrlEncoded
    @POST("proxy0001.json")
    Observable<AccessTokenRes> getAccessToken(@Field("proxy_code") String proxy_code,
                                              @Field("proxy_secret") String proxy_secret);

    /**
     * 微信预下单
     *
     * @param version
     * @param channel
     * @param body
     * @param trans_amount
     * @param access_token
     * @return
     */
    @FormUrlEncoded
    @POST("proxy2001.json")
    Observable<WeChatPreOrderRes> weChatPreOrder(@Field("version") String version,
                                                 @Field("channel") String channel,
                                                 @Field("body") String body,
                                                 @Field("trans_amount") String trans_amount,
                                                 @Field("access_token") String access_token);

    /**
     * 支付宝预下单
     *
     * @param version
     * @param channel
     * @param subject
     * @param body
     * @param trans_amount
     * @param access_token
     * @return
     */
    @FormUrlEncoded
    @POST("proxy3001.json")
    Observable<AlipayPreOrderRes> alipayPreOrder(@Field("version") String version,
                                                 @Field("channel") String channel,
                                                 @Field("subject") String subject,
                                                 @Field("body") String body,
                                                 @Field("trans_amount") String trans_amount,
                                                 @Field("access_token") String access_token);

    /**
     * 实名代收
     * @param trans_amount
     * @param bind_id
     * @param access_token
     * @return
     */
    @FormUrlEncoded
    @POST("proxy1001.json")
    Observable<RealNameCollectionRes> realNameCollction(@Field("trans_amount") String trans_amount,
                                                        @Field("bind_id") String bind_id,
                                                        @Field("access_token") String access_token);
}
