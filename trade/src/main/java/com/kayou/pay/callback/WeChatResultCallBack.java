package com.kayou.pay.callback;


import com.kayou.pay.entity.WeChatPreOrderRes;

public interface WeChatResultCallBack {
    void success(WeChatPreOrderRes res);

    void fail(String msg);
}
