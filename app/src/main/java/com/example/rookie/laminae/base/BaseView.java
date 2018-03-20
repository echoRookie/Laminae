package com.example.rookie.laminae.base;

import android.content.Context;

/**
 * Created by rookie on 2018/3/20.
 */

public interface BaseView {
    /*
    * 显示正在加载view
    * */
    void showLodaing();
    /*
    * 隐藏正在加载的view
    * */
    void hideLodaing();
    /*
    * 显示提示消息
    * */
    void showToast(String msg);
    /*
    * 显示错误消息
    * */
    void showError();
    /*
    * 获取上下文
    * */
    Context getContext();

}
