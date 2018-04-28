package com.example.rookie.laminae.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.rookie.laminae.API.ImageDetailAPI;
import com.example.rookie.laminae.API.TypeAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.httpUtils.RetrofitClient;
import com.example.rookie.laminae.main.classify.ClassifyAdapter;
import com.example.rookie.laminae.main.classify.ClassifyFragment;
import com.example.rookie.laminae.main.home.HomeFragment;
import com.example.rookie.laminae.user.UserInfoActivity;
import com.example.rookie.laminae.util.Base64;
import com.example.rookie.laminae.util.Constant;
import com.example.rookie.laminae.util.ImageLoadBuider;
import com.example.rookie.laminae.util.SPUtils;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private CircleImageView userIcon;
    private TextView username;
    private BottomNavigationBar bottomNavigationBar;
    private ClassifyFragment classifyFragment;
    private HomeFragment homeFragment;
    private Fragment myFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.inflateHeaderView(R.layout.navgation_header);
        navigationView.inflateMenu(R.menu.navgation_menu);
        fragmentManager = getSupportFragmentManager();

        init();
//      进入首页用fragment替换主布局
        homeFragment = new HomeFragment();
        classifyFragment = new ClassifyFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_content, homeFragment)
                .commit();
        myFragment = homeFragment;
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
//       底部导航栏的实现
            bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
            bottomNavigationBar.setBarBackgroundColor(R.color.colorPrimary);
            bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING)
                    .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
            bottomNavigationBar .addItem(new BottomNavigationItem(R.drawable.main_home,"Like").setActiveColorResource(R.color.bottomBar4))
                    .addItem(new BottomNavigationItem(R.drawable.main_home,"Home").setActiveColorResource(R.color.bottomBarHome))
                    .addItem(new BottomNavigationItem(R.drawable.main_home,"Music").setActiveColorResource(R.color.bottomBarLike))
                    .addItem(new BottomNavigationItem(R.drawable.main_home,"TV").setActiveColorResource(R.color.bottomBarTv))
                    .setFirstSelectedPosition(0)
                    .initialise();
           bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {

               @Override
               public void onTabSelected(int position) {

                   switch (position){
                       case 0:
                           switchFragment(homeFragment);
                           break;
                       case 1:
                           switchFragment(classifyFragment);
                           break;

                   }

               }

               @Override
               public void onTabUnselected(int position) {

               }

               @Override
               public void onTabReselected(int position) {

               }
           });
//            RetrofitClient client = RetrofitClient.getInstance();
//            ImageDetailAPI imageDetailAPI = client.createService(ImageDetailAPI.class);
//            Observable<ResponseBody> observable = imageDetailAPI.httpsPinsDetailRx(Base64.mClientInto,"1619723899");
//            observable.subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<ResponseBody>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//
//                        }
//
//                        @Override
//                        public void onNext(ResponseBody value) {
//                            try {
//                                Log.d("claass", "onNext: "+value.string());
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//                    });


        }

    }
//    替换fragment的方法
    private void switchFragment(Fragment fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if (myFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(myFragment)
                        .add(R.id.main_content, fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager().beginTransaction().hide(myFragment).show(fragment).commit();
            }
            myFragment = fragment;
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
