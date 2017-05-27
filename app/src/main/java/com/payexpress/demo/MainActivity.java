package com.payexpress.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.payexpress.trade.call.CallService;
import com.payexpress.trade.callback.AccessTokenCallBack;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.home_btn_gettoken) void clicked(){
        CallService.getAccessToken("862510159990373", "1093e46bb5c0822287f8e4499f59841a",
                new AccessTokenCallBack() {
                    @Override
                    public void getAccessTokenData(String data) {
                        mTokenInfo.setText(data);
                    }
                });
    }

    @OnClick(R.id.home_btn_getwechatorder) void getWechatOrder(){
        CallService.weChatPreOrder("1.0.0", "web", "广元燃气缴费", "0.01",
                mTokenInfo.getText().toString());
    }

    @OnClick(R.id.home_btn_alipayorder) void alipayOrder(){
        CallService.alipayPreOrder("1.0.0", "web", "广元燃气缴费","2017年5月燃气费", "0.01",
                mTokenInfo.getText().toString());
    }
}
