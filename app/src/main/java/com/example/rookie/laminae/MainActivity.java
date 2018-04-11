package com.example.rookie.laminae;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    private AnimationDrawable getBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        /取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        linearLayout  = (LinearLayout) findViewById(R.id.login_linearlayout);
        getBackground = (AnimationDrawable) linearLayout.getBackground();
        getBackground.setExitFadeDuration(4000);
        getBackground.setEnterFadeDuration(4000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBackground.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getBackground.stop();
    }
}
