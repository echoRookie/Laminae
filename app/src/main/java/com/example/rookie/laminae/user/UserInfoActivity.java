package com.example.rookie.laminae.user;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.dialog.BoardAddDialog;
import com.example.rookie.laminae.login.UserBean;
import com.example.rookie.laminae.user.UserBoard.UserBoardFragment;
import com.example.rookie.laminae.user.UserLike.UserLikeFragment;
import com.example.rookie.laminae.user.UserPins.UserPinsFragment;
import com.example.rookie.laminae.util.Base64;
import com.example.rookie.laminae.util.Constant;
import com.example.rookie.laminae.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class UserInfoActivity extends AppCompatActivity implements UserInfoView{
    private Toolbar toolbar;
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
    private ImageView addBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        toolbar = (Toolbar) findViewById(R.id.user_toolbar) ;
        addBoard = (ImageView) findViewById(R.id.user_add_board);
        addBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoardAddDialog boardAddDialog = new BoardAddDialog();
                boardAddDialog.show(getSupportFragmentManager(),"");
            }
        });
//      得到用户Id，初始化用户信息
        userId = getIntent().getIntExtra(Constant.USERID,0);
        haederBackground = (ImageView) findViewById(R.id.header_image_background);
        userIcon = (CircleImageView) findViewById(R.id.header_usericon);
        username = (TextView) findViewById(R.id.header_username);
        userFollowers = (TextView) findViewById(R.id.user_follower) ;
//      初始化用户关注信息
        myFragments = new ArrayList<>();
        myFragments.add(UserBoardFragment.newInstance(String.valueOf(userId)));
        myFragments.add(UserPinsFragment.newInstance(String.valueOf(userId)));
        myFragments.add(UserLikeFragment.newINstance(String.valueOf(userId)));
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
        String m = (String) SPUtils.get(getApplicationContext(),Constant.USERAUthorization,Base64.mClientInto);
        Log.d("userlogin", "onCreate: "+m);

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
//      设置标题栏
        toolbar.setTitle(userBean.getUsername());
        setSupportActionBar(toolbar);
        /*显示toolbar自带返回键设置标题*/
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /* 处理返回键的点击事件*/
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
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