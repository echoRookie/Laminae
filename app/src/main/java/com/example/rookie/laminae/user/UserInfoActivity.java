package com.example.rookie.laminae.user;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.rookie.laminae.API.UserAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.httpUtils.RetrofitClient;
import com.example.rookie.laminae.login.UserBean;
import com.example.rookie.laminae.util.Base64;
import com.example.rookie.laminae.util.Constant;
import com.example.rookie.laminae.util.FastBlurUtil;
import com.example.rookie.laminae.util.ImageLoadBuider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;
import okhttp3.ResponseBody;

public class UserInfoActivity extends AppCompatActivity implements UserInfoView{
    private int userId;
    private ImageView haederBackground;
    private CircleImageView userIcon;
    private TextView userFollowers;
    private TextView username;
    private TabLayout userTablayout;
    private ViewPager userViewpager;
    private List<Fragment> myFragments;
    private UserViewPagerAdapter myAdapter;
    private UserBean userBean;
    private UserInfoPresenter userInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
//      得到用户Id，初始化用户信息
        userId = getIntent().getIntExtra(Constant.USERID,0);
        haederBackground = (ImageView) findViewById(R.id.header_image_background);
        userIcon = (CircleImageView) findViewById(R.id.header_usericon);
        username = (TextView) findViewById(R.id.header_username);
        userFollowers = (TextView) findViewById(R.id.user_follower) ;
//      初始化用户关注信息
        myFragments = new ArrayList<>();
        myFragments.add(new Fragment());
        myFragments.add(new Fragment());
        myFragments.add(new Fragment());
        myFragments.add(new Fragment());
        userTablayout = (TabLayout) findViewById(R.id.user_tablayout);
        userViewpager = (ViewPager) findViewById(R.id.user_viewPager);
        myAdapter = new UserViewPagerAdapter(getSupportFragmentManager(),myFragments);
        userViewpager.setAdapter(myAdapter);
        userTablayout.setupWithViewPager(userViewpager);
        userTablayout.removeAllTabs();
//      初始化控制器
        userInfoPresenter = new UserInfoPresenter();
        userInfoPresenter.attachView(this);
        userInfoPresenter.getUserInfoData(String.valueOf(userId));

    }


    /**
     * 设置用户信息
     * @param  info
     */
    @Override
    public void setUserInfo(UserBean info) {
        this.userBean = info;
        String url = getString(R.string.url_image);
        Glide.with(getApplication())
                .load(url+userBean.getIcon_info().getKey())
                .crossFade(1000)
                .into(userIcon);

        Glide.with(getApplication())
                .load(url+userBean.getIcon_info().getKey())
                .override(50,50)
                .bitmapTransform(new BlurTransformation(this,25))
                .crossFade(1000)
                .into(haederBackground);
        username.setText(userBean.getUsername());
        userFollowers.setText("粉丝 | "+userBean.getFollower_count());
        Log.d("userAC", "setUserInfo: "+userBean.getBoard_count());
        for (int i=0;i<myAdapter.getCount();i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.user_tab_item, null);
            TextView userTitle = (TextView) view.findViewById(R.id.tab_item_title);
            TextView userNum = (TextView) view.findViewById(R.id.tab_item_num);
            if(i==0){
                userTitle.setText("画板");
                userNum.setText(String.valueOf(userBean.getBoard_count()));
                userTablayout.addTab(userTablayout.newTab().setCustomView(view),true);

            }
            if(i==1){
                userTitle.setText("采集");
                userNum.setText(String.valueOf(userBean.getPin_count()));
                userTablayout.addTab(userTablayout.newTab().setCustomView(view));

            }
            if(i==2){
                userTitle.setText("喜欢");
                userNum.setText(String.valueOf(userBean.getLike_count()));
                userTablayout.addTab(userTablayout.newTab().setCustomView(view));

            }
            if(i==3){
                userTitle.setText("关注");
                userNum.setText(String.valueOf(userBean.getFollowing_count()));

                userTablayout.addTab(userTablayout.newTab().setCustomView(view));

            }
        }
    }

    @Override
    public void showLodaing() {

    }

    @Override
    public void hideLodaing() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showError() {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userInfoPresenter.detachView();
    }
}