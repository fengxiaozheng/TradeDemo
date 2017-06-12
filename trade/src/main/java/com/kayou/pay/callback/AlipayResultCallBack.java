package com.kayou.pay.callback;


import com.kayou.pay.entity.AlipayPreOrderRes;

public interface AlipayResultCallBack {
    void success(AlipayPreOrderRes res);

    void fail(String msg);
}
