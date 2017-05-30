package com.payexpress.trade.call;


import android.support.annotation.NonNull;

import com.payexpress.trade.callback.AccessTokenCallBack;
import com.payexpress.trade.callback.RealNameCollectionCallBack;
import com.payexpress.trade.dohttp.AlipayPreOrder;
import com.payexpress.trade.dohttp.GetAccessToken;
import com.payexpress.trade.dohttp.RealNameCollection;
import com.payexpress.trade.dohttp.WeChatPreOrder;
import com.payexpress.trade.entity.AccessTokenRes;
import com.payexpress.trade.entity.AlipayPreOrderRes;
import com.payexpress.trade.entity.RealNameCollectionRes;
import com.payexpress.trade.entity.WeChatPreOrderRes;
import com.payexpress.trade.http.ApiCallback;
import com.payexpress.trade.http.RetrofitUtils;
import com.payexpress.trade.http.SubscriberCallBack;

/**
 * 外部接口调用统一服务类
 */
public class CallService {

    /**
     * 获取access_token
     *
     * @param proxy_code
     * @param proxy_secret
     * @param callBack
     */
    public static void getAccessToken(@NonNull String proxy_code, @NonNull String proxy_secret,
                                      final AccessTokenCallBack callBack) {
        RetrofitUtils.getInstance().doHttp(new GetAccessToken(
                new SubscriberCallBack<>(new ApiCallback<AccessTokenRes>() {
                    @Override
                    public void onSuccess(AccessTokenRes res) {
                        callBack.getAccessTokenData(res.getAccess_token());
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        System.out.println("getAccessTokenError:" + msg);
                    }

                    @Override
                    public void onCompleted() {

                    }
                }), proxy_code, proxy_secret));
    }

    /**
     * 微信预下单
     *
     * @param version
     * @param channel
     * @param body
     * @param trans_amount
     * @param access_token
     */
    public static void weChatPreOrder(@NonNull String version, @NonNull String channel,
                                      @NonNull String body, @NonNull String trans_amount,
                                      @NonNull String access_token) {
        RetrofitUtils.getInstance().doHttp(new WeChatPreOrder(
                new SubscriberCallBack<>(new ApiCallback<WeChatPreOrderRes>() {
                    @Override
                    public void onSuccess(WeChatPreOrderRes res) {
                        System.out.println("WeChatOrderRes:" + res.getAccess_token());
                        sendToWeChat(res);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        System.out.println("WeChatOrderError:" + msg);
                    }

                    @Override
                    public void onCompleted() {

                    }
                }), version, channel, body, trans_amount, access_token
        ));
    }

    /**
     * 支付宝预下单
     *
     * @param version
     * @param channel
     * @param subject
     * @param body
     * @param trans_amount
     * @param access_token
     */
    public static void alipayPreOrder(@NonNull String version, @NonNull String channel,
                                      @NonNull String subject, @NonNull String body,
                                      @NonNull String trans_amount, @NonNull String access_token) {
        RetrofitUtils.getInstance().doHttp(new AlipayPreOrder(
                new SubscriberCallBack<>(new ApiCallback<AlipayPreOrderRes>() {
                    @Override
                    public void onSuccess(AlipayPreOrderRes res) {

                    }

                    @Override
                    public void onFailure(int code, String msg) {

                    }

                    @Override
                    public void onCompleted() {

                    }
                }), version, channel, subject, body, trans_amount, access_token
        ));
    }

    /**
     * 实名认证
     *
     * @param trans_amount
     * @param bind_id
     * @param access_token
     */
    public static void realNameCollection(@NonNull String trans_amount, @NonNull String bind_id,
                                          @NonNull String access_token, final RealNameCollectionCallBack callBack) {
        RetrofitUtils.getInstance().doHttp(new RealNameCollection(
                new SubscriberCallBack<>(new ApiCallback<RealNameCollectionRes>() {
                    @Override
                    public void onSuccess(RealNameCollectionRes res) {
                        callBack.collectionResult(true, "认证成功");
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        callBack.collectionResult(false, msg);
                    }

                    @Override
                    public void onCompleted() {

                    }
                }), trans_amount, bind_id, access_token
        ));
    }



    private static void sendToWeChat(WeChatPreOrderRes res){

    }
    
}
