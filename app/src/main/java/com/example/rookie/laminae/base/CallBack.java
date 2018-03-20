package com.example.rookie.laminae.base;

/**
 * Created by rookie on 2018/3/20.
 * 请求数据用到的回调接口
 */

public interface CallBack <T> {
    /*
    * 数据请求成功
    * */
    public void onSuccess();
    /*
    * 数据请求成功但无法返回正常数据，如空数据等
    * */
    public void onFailure();
    /*
    * 数据请求失败，如无网络权限，无联网
    * */
    public void onError();
    /*
    * 数据请求完成后所执行的操作
    * */
    public void onCompleted();
}
