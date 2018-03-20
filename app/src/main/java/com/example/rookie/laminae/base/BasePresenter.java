package com.example.rookie.laminae.base;

/**
 * Created by rookie on 2018/3/20.
 */

public class BasePresenter <T extends BaseView>{
    /*
    * 需要绑定的view
    * */
    private T myView;
    /*
    * 在活动中绑定view
    * */
    public void attachView(T view){
        this.myView = view;
    }
    /*
    * 断开连接
    * */
    public void detachView(){
        this.myView = null;
    }
    /*
    * 判断view是否为空
    * */
    public boolean isViewAttached(){
        return myView != null;
    }
    /*
    * 获取连接的view
    * */
    public T getView(){
        return myView;
    }

}
