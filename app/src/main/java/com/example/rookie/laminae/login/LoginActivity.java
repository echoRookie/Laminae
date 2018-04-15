package com.example.rookie.laminae.login;

import android.graphics.drawable.AnimationDrawable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.rookie.laminae.R;
import com.example.rookie.laminae.base.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginView {
    private LinearLayout linearLayout;
    private AnimationDrawable background;
    private LoginPresenter loginPresenter;
    private TextInputEditText usernameText;
    private TextInputEditText passwordText;
    private Button loginButton;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dissmissWindowbar();
        setContentView(R.layout.activity_login);
//      初始化控件
        usernameText = (TextInputEditText) findViewById(R.id.loginText) ;
        passwordText = (TextInputEditText) findViewById(R.id.loginPassword);
        loginButton = (Button) findViewById(R.id.btn_login);
//      初始化控制器
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
//      初始化动态背景
        linearLayout = (LinearLayout) findViewById(R.id.login_linearlayout);
        background = (AnimationDrawable) linearLayout.getBackground();
        background.setEnterFadeDuration(4000);
        background.setExitFadeDuration(4000);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              登录检查
                username = usernameText.getText().toString();
                password = passwordText.getText().toString();
                if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
                    Snackbar.make(v, R.string.login_tips,Snackbar.LENGTH_SHORT).show();
                }
                else{
                loginPresenter.getLoginApiData(username,password);
                loginButton.setText("登陆中...");
                }
            }
        });
    }

    /**
     * 取消状态栏
     */
    public void dissmissWindowbar(){
//      取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//      取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    /**
     * 登录成功，进入到home页
     */
    @Override
    public void navigateToHome() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        background.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
        background.stop();
    }
}
