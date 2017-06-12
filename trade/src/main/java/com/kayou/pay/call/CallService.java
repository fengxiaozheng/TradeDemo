package com.kayou.pay.call;


import android.support.annotation.NonNull;

import com.kayou.pay.callback.AlipayResultCallBack;
import com.kayou.pay.dohttp.AlipayRefund;
import com.kayou.pay.dohttp.GetAccessToken;
import com.kayou.pay.dohttp.WeChatPreOrder;
import com.kayou.pay.dohttp.WeChatQuery;
import com.kayou.pay.entity.AlipayPreOrderRes;
import com.kayou.pay.http.ApiCallback;
import com.kayou.pay.http.ResponseResult;
import com.kayou.pay.http.RetrofitUtils;
import com.kayou.pay.callback.AccessTokenCallBack;
import com.kayou.pay.callback.CommonCallBack;
import com.kayou.pay.callback.WeChatResultCallBack;
import com.kayou.pay.dohttp.AlipayPreOrder;
import com.kayou.pay.dohttp.AlipayQuery;
import com.kayou.pay.dohttp.WeChatRefund;
import com.kayou.pay.entity.AccessTokenRes;
import com.kayou.pay.entity.WeChatPreOrderRes;
import com.kayou.pay.http.SubscriberCallBack;

import org.json.JSONException;
import org.json.JSONObject;

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
        JSONObject map = new JSONObject();
        try {
            map.put("proxy_code", proxy_code);
            map.put("proxy_secret", proxy_secret);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                }), map.toString()));
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
                                 @NonNull String trans_amount, @NonNull String access_token,
                                      final AlipayResultCallBack callBack) {
        JSONObject json = new JSONObject();
        try {
            json.put("version", version);
            json.put("channel", channel);
            json.put("subject", subject);
            json.put("body", body);
            json.put("trans_amount", trans_amount);
            json.put("access_token", access_token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RetrofitUtils.getInstance().doHttp(new AlipayPreOrder(
                new SubscriberCallBack<>(new ApiCallback<AlipayPreOrderRes>() {
                    @Override
                    public void onSuccess(AlipayPreOrderRes res) {
                        callBack.success(res);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                    callBack.fail(msg);
                    }

                    @Override
                    public void onCompleted() {

                    }
                }), json.toString()
        ));
    }

    /**
     * 查询支付宝支付结果
     * @param version
     * @param channel
     * @param pay_trans_no
     * @param access_token
     * @param callBack
     */
    public static void alipayQuery(@NonNull String version, @NonNull String channel,
                                   @NonNull final String pay_trans_no,
                                   @NonNull String access_token, final CommonCallBack callBack){
        JSONObject json = new JSONObject();
        try {
            json.put("version", version);
            json.put("channel", channel);
            json.put("pay_trans_no", pay_trans_no);
            json.put("access_token", access_token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RetrofitUtils.getInstance().doHttp(new AlipayQuery(
                new SubscriberCallBack<>(new ApiCallback<ResponseResult>() {
                    @Override
                    public void onSuccess(ResponseResult res) {
                        callBack.success();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        callBack.fail(msg);
                    }

                    @Override
                    public void onCompleted() {

                    }
                }), json.toString()
        ));

    }

    /**
     * 支付宝退款
     * @param version
     * @param channel
     * @param trade_no
     * @param refund_reason
     * @param pay_trans_no
     * @param access_token
     * @param callBack
     */
    public static void alipayRefund(@NonNull String version, @NonNull String channel,
                                    @NonNull String trade_no, @NonNull String refund_reason,
                                    @NonNull String pay_trans_no, @NonNull String access_token,
                                    final CommonCallBack callBack){
        JSONObject json = new JSONObject();
        try {
            json.put("version", version);
            json.put("channel", channel);
            json.put("trade_no", trade_no);
            json.put("refund_reason", refund_reason);
            json.put("pay_trans_no", pay_trans_no);
            json.put("access_token", access_token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RetrofitUtils.getInstance().doHttp(new AlipayRefund(
                new SubscriberCallBack<>(new ApiCallback<ResponseResult>() {
                    @Override
                    public void onSuccess(ResponseResult res) {
                        callBack.success();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        callBack.fail(msg);
                    }

                    @Override
                    public void onCompleted() {

                    }
                }), json.toString()
        ));
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
                                      @NonNull String access_token, final WeChatResultCallBack callBack) {
        JSONObject json = new JSONObject();
        try {
            json.put("version", version);
            json.put("channel", channel);
            json.put("body", body);
            json.put("trans_amount", trans_amount);
            json.put("access_token", access_token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RetrofitUtils.getInstance().doHttp(new WeChatPreOrder(
                new SubscriberCallBack<>(new ApiCallback<WeChatPreOrderRes>() {
                    @Override
                    public void onSuccess(WeChatPreOrderRes res) {
                        callBack.success(res);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        callBack.fail(msg);
                    }

                    @Override
                    public void onCompleted() {

                    }
                }), json.toString()
        ));
    }

    /**
     * 查询微信支付状态
     * @param version
     * @param channel
     * @param pay_trans_no
     * @param access_token
     * @param callBack
     */
    public static void WeChatQuery(@NonNull String version, @NonNull String channel,
                                   @NonNull String pay_trans_no, @NonNull String access_token,
                                   final CommonCallBack callBack){
        JSONObject json = new JSONObject();
        try {
            json.put("version", version);
            json.put("channel", channel);
            json.put("pay_trans_no", pay_trans_no);
            json.put("access_token", access_token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RetrofitUtils.getInstance().doHttp(new WeChatQuery(
                new SubscriberCallBack<>(new ApiCallback<ResponseResult>() {
                    @Override
                    public void onSuccess(ResponseResult res) {
                        callBack.success();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        callBack.fail(msg);
                    }

                    @Override
                    public void onCompleted() {

                    }
                }), json.toString()
        ));
    }

    /**
     * 微信退款
     * @param version
     * @param channel
     * @param pay_trans_no
     * @param access_token
     * @param callBack
     */
    public static void WeChatRefund(@NonNull String version, @NonNull String channel,
                                    @NonNull String pay_trans_no, @NonNull String access_token,
                                    final CommonCallBack callBack){
        JSONObject json = new JSONObject();
        try {
            json.put("version", version);
            json.put("channel", channel);
            json.put("pay_trans_no", pay_trans_no);
            json.put("access_token", access_token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RetrofitUtils.getInstance().doHttp(new WeChatRefund(
                new SubscriberCallBack<>(new ApiCallback<ResponseResult>() {
                    @Override
                    public void onSuccess(ResponseResult res) {
                        callBack.success();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        callBack.fail(msg);
                    }

                    @Override
                    public void onCompleted() {

                    }
                }), json.toString()
        ));
    }
}
