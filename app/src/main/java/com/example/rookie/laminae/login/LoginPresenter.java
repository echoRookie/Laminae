package com.example.rookie.laminae.login;


import android.util.Log;

import com.example.rookie.laminae.base.BasePresenter;
import com.example.rookie.laminae.base.CallBack;
import com.example.rookie.laminae.util.Constant;

/**
 * Created by rookie on 2018/4/12.
 */

public class LoginPresenter extends BasePresenter<LoginView> implements CallBack<UserBean> {
    private LoginModel loginModel;
    public LoginPresenter(){
        this.loginModel = new LoginModel();
    };
    public void getLoginApiData(String username,String password){
        loginModel.getLoginApi(username,password,this);
    };
    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure() {
        getView().showToast("账号密码错误");

    }

    @Override
    public void onError() {
         getView().showError();
    }

    @Override
    public void onCompleted(UserBean bean) {
        Log.d("rrr", "onCompleted: ");
        getView().setUserInfo(bean);
        getView().navigateToHome();

    }
}
