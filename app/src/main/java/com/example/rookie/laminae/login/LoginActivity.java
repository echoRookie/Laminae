package com.example.rookie.laminae.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rookie.laminae.R;
import com.example.rookie.laminae.base.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginView {
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
    }

    @Override
    public void navigateToHome() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }
}
