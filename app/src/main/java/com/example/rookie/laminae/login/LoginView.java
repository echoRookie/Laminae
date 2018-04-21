package com.example.rookie.laminae.login;

import com.example.rookie.laminae.base.BaseView;

/**
 * Created by rookie on 2018/4/12.
 */

public interface LoginView extends BaseView {
    void navigateToHome();
    void setUserInfo(UserBean userBean);
}
