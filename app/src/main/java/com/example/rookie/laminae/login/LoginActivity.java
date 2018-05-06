package com.example.rookie.laminae.login;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.rookie.laminae.main.MainActivity;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.base.BaseActivity;
import com.example.rookie.laminae.user.UserInfoActivity;
import com.example.rookie.laminae.util.Constant;
import com.example.rookie.laminae.util.SPUtils;

public class LoginActivity extends BaseActivity implements LoginView {
    private LinearLayout linearLayout;
    private AnimationDrawable background;
    private LoginPresenter loginPresenter;
    private TextInputEditText usernameText;
    private TextInputEditText passwordText;
    private Button loginButton;
    private Button registerButton;
    private String username;
    private String password;
    private UserBean userBean  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dissmissWindowbar();
        setContentView(R.layout.activity_login);
        if((Boolean) SPUtils.get(getContext(),Constant.ISLOGIN,false)){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
//      初始化控件
        usernameText = (TextInputEditText) findViewById(R.id.loginText) ;
        passwordText = (TextInputEditText) findViewById(R.id.loginPassword);
        loginButton = (Button) findViewById(R.id.btn_login);
        registerButton = (Button) findViewById(R.id.btn_register);

        registerButton.setText(R.string.regist);

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
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this,UserInfoActivity.class);
//                intent.putExtra(Constant.USERBEAN,userBean);
//                startActivity(intent);
                Intent intent = new Intent();
                intent.setData(Uri.parse(getString(R.string.urlRegister)));
                startActivity(intent);
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
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();


    }

    /**
     * 设置保存用户信息
     * @param userBean
     */
    @Override
    public void setUserInfo(UserBean userBean) {
        this.userBean = userBean;
//      保存已登录用户的信息
        if(userBean!=null){
            SPUtils.putApply(getContext(),Constant.ISLOGIN,true);
            SPUtils.putApply(getContext(),Constant.USERID,userBean.getUser_id());
            SPUtils.putApply(getContext(),Constant.USERNAME,userBean.getUsername());
            SPUtils.putApply(getContext(),Constant.PASSWORD,password);
            SPUtils.putApply(getContext(),Constant.USEREMAIL,userBean.getEmail());
            SPUtils.putApply(getContext(),Constant.USERICONKEY,userBean.getIcon_info().getKey());
        }
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
