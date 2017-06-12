package com.kayou.electric.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.kayou.electric.Constants;
import com.kayou.electric.MainActivity;
import com.kayou.electric.R;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zft.pay.call.CallService;
import com.zft.pay.callback.CommonCallBack;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	@BindView(R.id.wx_tv_result)
	TextView payResult;

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_result);
		ButterKnife.bind(this);
    	api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		String ss;
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			switch (resp.errCode){
				case BaseResp.ErrCode.ERR_OK:
					query();
					break;
				case BaseResp.ErrCode.ERR_USER_CANCEL:
					ss = "用户点击了取消";
					payResult.setText("支付失败");
					Toast.makeText(this, ss, Toast.LENGTH_SHORT).show();
				//	WXPayEntryActivity.this.finish();
					break;
				case BaseResp.ErrCode.ERR_AUTH_DENIED:
					ss = "授权失败";
					payResult.setText("支付失败");
					Toast.makeText(this, ss, Toast.LENGTH_SHORT).show();
				//	WXPayEntryActivity.this.finish();
					break;
				case BaseResp.ErrCode.ERR_SENT_FAILED:
					ss = "发送失败";
					payResult.setText("支付失败");
					Toast.makeText(this, ss, Toast.LENGTH_SHORT).show();
				//	WXPayEntryActivity.this.finish();
					break;
				case BaseResp.ErrCode.ERR_UNSUPPORT:
					ss = "不支持";
					payResult.setText("支付失败");
					Toast.makeText(this, ss, Toast.LENGTH_SHORT).show();
				//	WXPayEntryActivity.this.finish();
					break;
				case BaseResp.ErrCode.ERR_COMM:
					ss = "普通错误";
					payResult.setText("支付失败");
					Toast.makeText(this, ss, Toast.LENGTH_SHORT).show();
				//	WXPayEntryActivity.this.finish();
					break;
				default:
					break;
			}
		}
	}

	//向自己服务器查询
	private void query(){
		CallService.WeChatQuery("1.0.0", "web", MainActivity.wx_paytrans_no,
				MainActivity.access_token, new CommonCallBack() {
					@Override
					public void success() {
						//支付成功
						payResult.setText("支付成功");
						Toast.makeText(WXPayEntryActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
					//	WXPayEntryActivity.this.finish(); //finish后回到demo主界面
					}

					@Override
					public void fail(String msg) {
						payResult.setText("支付失败");
						Toast.makeText(WXPayEntryActivity.this, msg, Toast.LENGTH_SHORT).show();
					//	WXPayEntryActivity.this.finish();
					}
				});
	}
}