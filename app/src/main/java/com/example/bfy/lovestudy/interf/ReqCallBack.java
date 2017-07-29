package com.example.bfy.lovestudy.interf;

/**
 * Created by bfy on 17-5-31.
 */

public interface ReqCallBack<T> {
    /**
     * 响应成功
     */
    void onReqSuccess(T result);

    /**
     * 响应失败
     */
    void onReqFailed(String errorMsg);

}
