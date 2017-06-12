package com.kayou.electric;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zft.pay.call.CallService;
import com.zft.pay.callback.AccessTokenCallBack;
import com.zft.pay.callback.AlipayResultCallBack;
import com.zft.pay.callback.CommonCallBack;
import com.zft.pay.callback.WeChatResultCallBack;
import com.zft.pay.entity.AlipayPreOrderRes;
import com.zft.pay.entity.WeChatPreOrderRes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.home_btn_gettoken)
    Button mGetInfo;
    @BindView(R.id.home_tv_tokeninfo)
    TextView mTokenInfo;
    @BindView(R.id.home_btn_getwechatorder)
    Button mWeChatOrder;
    @BindView(R.id.home_tv_wechatorderinfo)
    TextView mWeChatOrderInfo;
    @BindView(R.id.home_btn_alipayorder)
    Button mAlipayOrder;
    @BindView(R.id.home_tv_alipayorderinfo)
    TextView mAlipayOrderInfo;
    @BindView(R.id.home_btn_alipayrefund)
    Button mAlipayRefund;
    @BindView(R.id.home_tv_alipayrefundinfo)
    TextView mAlipayRefundInfo;
    @BindView(R.id.home_btn_getwechatrefund)
    Button mWeChatRefund;
    @BindView(R.id.home_tv_wechatrefundinfo)
    TextView mWeChatRefundInfo;

    private static final int SDK_PAY_FLAG = 1;

    //向服务器查询支付状态参数
    private String getTrade_no;
    private String getPay_trans_no;

    //支付宝支付所需参数
    private String orderInfo;

    public static String access_token;
    public static String wx_paytrans_no;

    IWXAPI api;


    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    try {
                        JSONObject object = new JSONObject(resultInfo);
                        JSONObject object1 = object.getJSONObject("alipay_trade_app_pay_response");
                        getTrade_no = object1.getString("trade_no");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        //支付宝扣款成功后在此调用查询接口向自己服务器查询支付状态
                        Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        CallService.alipayQuery("1.0.0", "web", getPay_trans_no,
                                mTokenInfo.getText().toString(), new CommonCallBack() {
                                    @Override
                                    public void success() {
                                        //完成支付过程
                                        mAlipayOrderInfo.setText("支付成功");
                                    }

                                    @Override
                                    public void fail(String msg) {
                                        mAlipayOrderInfo.setText(msg);
                                    }
                                });
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(MainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;

                }
                default:
                    break;

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //注册到微信
        api = WXAPIFactory.createWXAPI(MainActivity.this, Constants.APP_ID);
    }



    //获取acces_token
    @OnClick(R.id.home_btn_gettoken) void clicked(){
        CallService.getAccessToken("862510159990373", "1093e46bb5c0822287f8e4499f59841a",
                new AccessTokenCallBack() {
                    @Override
                    public void getAccessTokenData(String data) {
                        mTokenInfo.setText(data);
                        access_token = data;
                    }
                });
    }

    //微信预下单
    @OnClick(R.id.home_btn_getwechatorder) void getWechatOrder(){
        CallService.weChatPreOrder("1.0.0", "web", "广元燃气缴费", "0.01",
                mTokenInfo.getText().toString(), new WeChatResultCallBack() {
                    @Override
                    public void success(WeChatPreOrderRes res) {
                        wx_paytrans_no = res.getPay_trans_no();
                        //预下单成功后，调用微信支付
                        PayReq req = new PayReq();
                        req.appId = Constants.APP_ID;
                        req.partnerId = res.getPartner_id();
                        req.prepayId = res.getPrepay_id();
                        req.packageValue = res.getWx_package();
                        req.nonceStr = res.getNonce_str();
                        req.timeStamp = res.getTimestamp();
                        req.sign = res.getSign();
                        api.sendReq(req);
                    }

                    @Override
                    public void fail(String msg) {
                        mWeChatOrderInfo.setText(msg);
                    }
                });
    }

    //微信退款
    @OnClick(R.id.home_btn_getwechatrefund) void weChatRefund(){
        CallService.WeChatRefund("1.0.0", "web", wx_paytrans_no,
                mTokenInfo.getText().toString(), new CommonCallBack() {
                    @Override
                    public void success() {
                        mWeChatRefundInfo.setText("退款成功");
                    }

                    @Override
                    public void fail(String msg) {
                        mWeChatRefundInfo.setText(msg);
                    }
                });
    }

    //支付宝预下单
    @OnClick(R.id.home_btn_alipayorder) void alipayOrder(){
        CallService.alipayPreOrder("1.0.0", "web", "广元燃气缴费", "2017年5月燃气费", "0.01",
                mTokenInfo.getText().toString(), new AlipayResultCallBack() {
                    @Override
                    public void success(AlipayPreOrderRes res) {
                        getPay_trans_no = res.getPay_trans_no();
                        //预下单成功后，开始调用支付宝支付
                        orderInfo = res.getOrder_info();
                        Runnable payRunnable = new Runnable() {
                            @Override
                            public void run() {
                                PayTask alipay = new PayTask(MainActivity.this);
                                Map<String, String> result = alipay.payV2(orderInfo, true);
                                Log.i("msp", result.toString());

                                Message msg = new Message();
                                msg.what = SDK_PAY_FLAG;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            }
                        };
                        Thread payThread = new Thread(payRunnable);
                        payThread.start();
                    }

                    @Override
                    public void fail(String msg) {
                        mAlipayOrderInfo.setText(msg);
                    }
                });
    }

    //支付宝退款
    @OnClick(R.id.home_btn_alipayrefund) void alipayRefund(){
        CallService.alipayRefund("1.0.0", "web", getTrade_no, "测试", getPay_trans_no,
                mTokenInfo.getText().toString(), new CommonCallBack() {
                    @Override
                    public void success() {
                        mAlipayRefundInfo.setText("退款成功");
                    }

                    @Override
                    public void fail(String msg) {
                        mAlipayRefundInfo.setText(msg);
                    }
                });
    }
}
