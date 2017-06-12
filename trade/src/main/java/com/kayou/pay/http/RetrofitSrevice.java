package com.kayou.pay.http;


import com.kayou.pay.entity.AccessTokenRes;
import com.kayou.pay.entity.AlipayPreOrderRes;
import com.kayou.pay.entity.WeChatPreOrderRes;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface RetrofitSrevice {
    //  public static final String BASE_URL = "http://192.168.201.109:8080/payexpress/";
    public static final String BASE_URL = "http://182.140.224.136:7080/payexpress/";


    /**
     * 获取access_token
     * @param req
     * @return
     */
    @POST("proxy0001.json")
    Observable<AccessTokenRes> getAccessToken(@Body String req);

    /**
     * 微信预下单
     * @param req
     * @return
     */
    @POST("proxy2001.json")
    Observable<WeChatPreOrderRes> weChatPreOrder(@Body String req);

    /**
     * 微信支付状态查询
     * @param req
     * @return
     */
    @POST("proxy2002.json")
    Observable<ResponseResult> weChatQuery(@Body String req);

    /**
     * 微信退款
     * @param req
     * @return
     */
    @POST("proxy2003.json")
    Observable<ResponseResult> weChatRefund(@Body String req);

    /**
     * 支付宝预下单
     * @param req
     * @return
     */
    @POST("proxy3001.json")
    Observable<AlipayPreOrderRes> alipayPreOrder(@Body String req);

    /**
     * 支付宝订单查询
     * @param req
     * @return
     */
    @POST("proxy3002.json")
    Observable<ResponseResult> alipayQuery(@Body String req);

    /**
     * 支付宝退款
     * @param req
     * @return
     */
    @POST("proxy3003.json")
    Observable<ResponseResult> alipayRefund(@Body String req);
}
