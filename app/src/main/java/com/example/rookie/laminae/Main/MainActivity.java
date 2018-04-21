package com.example.rookie.laminae.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rookie.laminae.API.TokenAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.login.UserBean;
import com.example.rookie.laminae.user.UserInfoActivity;
import com.example.rookie.laminae.util.Constant;
import com.example.rookie.laminae.util.ImageLoadBuider;
import com.example.rookie.laminae.util.SPUtils;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private CircleImageView userIcon;
    private TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.inflateHeaderView(R.layout.navgation_header);
        navigationView.inflateMenu(R.menu.navgation_menu);
        init();


    }

    /**
     * 初始化navigation的头部控件
     * username 用户名
     * usericon 用户头像
     */
    public void init(){
        View view = navigationView.getHeaderView(0);
        userIcon = (CircleImageView) view.findViewById(R.id.nav_header_image);
        username = (TextView) view.findViewById(R.id.nav_header_name);
//      检查用户的登录状态
        if(SPUtils.get(getApplicationContext(),Constant.ISLOGIN,false)!=null){
            String usernameStatus = (String) SPUtils.get(getApplicationContext(),Constant.USERNAME,getString(R.string.userneme_status));
            username.setText(usernameStatus);
            userIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,UserInfoActivity.class);
                    int  userId = (int) SPUtils.get(getApplicationContext(),Constant.USERID,0);
                    intent.putExtra(Constant.USERID,userId);
                    startActivity(intent);
                }
            });
            String userIconUrl = (String) SPUtils.get(getApplicationContext(),Constant.USERICONKEY,"");
            ImageLoadBuider.ImageLoadfitCenter(this,userIcon,userIconUrl);
        }

    }
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
