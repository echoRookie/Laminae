package com.example.rookie.laminae.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.rookie.laminae.aboutme.AboutMeActivity;
import com.example.rookie.laminae.api.NewsAPI;
import com.example.rookie.laminae.db.Category;
import com.example.rookie.laminae.entity.NewsDataDetial;
import com.example.rookie.laminae.entity.NewsList;
import com.example.rookie.laminae.error.ErrorFragment;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.follow.UserFollowActivity;
import com.example.rookie.laminae.httputils.NewsRetrofitClient;
import com.example.rookie.laminae.main.classify.SelectAdapter;
import com.example.rookie.laminae.main.classify.UnselectAdapter;
import com.example.rookie.laminae.main.news.NewsFragment;
import com.example.rookie.laminae.search.SearchActivity;
import com.example.rookie.laminae.login.LoginActivity;
import com.example.rookie.laminae.main.classify.ClassifyFragment;
import com.example.rookie.laminae.main.home.HomeFragment;
import com.example.rookie.laminae.user.UserInfoActivity;
import com.example.rookie.laminae.util.Constant;
import com.example.rookie.laminae.util.ImageLoadBuider;
import com.example.rookie.laminae.util.SPUtils;
import com.google.gson.Gson;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private CircleImageView userIcon;
    private TextView username;
    private BottomNavigationBar bottomNavigationBar;
    private ClassifyFragment classifyFragment;
    private HomeFragment homeFragment;
    private NewsFragment newsFragment;
    private ErrorFragment errorFragment;
    private Fragment myFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private long mExitTime;
    private PopupWindow window;//弹出popupwindow窗体
    private Window myWindow;
    private ImageView popupWindowBack;
    private RecyclerView selectRecycler;
    private RecyclerView unSelectRecycler;
    private SelectAdapter selectAdapter;
    private UnselectAdapter unSelectAdapter;
    private List<String> selectListName;
    private List<String> selectListType;
    private List<String> unselectListName;
    private List<String> unselectListType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.inflateHeaderView(R.layout.navgation_header);
        navigationView.inflateMenu(R.menu.navgation_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.MyDrawerLayout);
//      侧面导航栏的点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_logout){
                    drawerLayout.closeDrawers();
                    SPUtils.clear(getApplicationContext());
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(item.getItemId() == R.id.nav_search){
                    drawerLayout.closeDrawers();
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() ==R.id.nav_follow){
                    drawerLayout.closeDrawers();
                    Intent intent = new Intent(MainActivity.this, UserFollowActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_about){
                    drawerLayout.closeDrawers();
                    Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle("花瓣");
        setSupportActionBar(toolbar);
//      设置状态栏和标题栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.bottomBarHomeDark));
        }
        toolbar.setBackgroundColor(getResources().getColor(R.color.bottomBarHome));
        fragmentManager = getSupportFragmentManager();

        init();
//      进入首页用fragment替换主布局
        homeFragment = new HomeFragment();
        classifyFragment = new ClassifyFragment();
        errorFragment = new ErrorFragment();
        newsFragment = new NewsFragment();
//        if(NetUtils.isConnected(getApplicationContext())){
            fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.main_content, homeFragment).commit();
            myFragment =homeFragment ;
//        }
//        else {
//
//            fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.add(R.id.main_content, errorFragment)
//                    .commit();
//            myFragment = errorFragment;
//        }
    }

    /**
     * 初始化navigation的头部控件
     * username 用户名
     * usericon 用户头像
     */
    public void init(){
        setCategory();
        View view = navigationView.getHeaderView(0);
        userIcon = (CircleImageView) view.findViewById(R.id.nav_header_image);
        username = (TextView) view.findViewById(R.id.nav_header_name);
//      检查用户的登录状态
        if(SPUtils.get(getApplicationContext(),Constant.ISLOGIN,false)!=null){
            String usernameStatus = (String) SPUtils.get(getApplicationContext(),Constant.USERNAME,getString(R.string.userneme_status));
            username.setText(usernameStatus);
            String userIconKey = (String) SPUtils.get(getApplicationContext(),Constant.USERICONKEY,"");
            ImageLoadBuider.ImageLoadCenterCrop(getApplicationContext(),userIcon,userIconKey);
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
            bottomNavigationBar .addItem(new BottomNavigationItem(R.drawable.main_home,"Home").setActiveColorResource(R.color.bottomBarHome))
                    .addItem(new BottomNavigationItem(R.drawable.main_classify,"Classify").setActiveColorResource(R.color.bottomBarClassify))
                    .addItem(new BottomNavigationItem(R.drawable.main_more,"More").setActiveColorResource(R.color.bottomBarMore))
                    .setFirstSelectedPosition(0)
                    .initialise();
            bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {

               @Override
               public void onTabSelected(int position) {

                   switch (position){
                       case 0:
                           switchFragment(homeFragment);
                           toolbar.setBackgroundColor(getResources().getColor(R.color.bottomBarHome));
                           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                               Window window = getWindow();
                               window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                                       | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                               window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                               window.setStatusBarColor(getResources().getColor(R.color.bottomBarHomeDark));
                           }
                           break;
                       case 1:
                           switchFragment(classifyFragment);
                           toolbar.setBackgroundColor(getResources().getColor(R.color.bottomBarClassify));
                           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                               Window window = getWindow();
                               window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                                       | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                               window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                               window.setStatusBarColor(getResources().getColor(R.color.bottomBarClassifyDark));
                           }
                           break;
                       case 2:
                           switchFragment(newsFragment);
                           toolbar.setBackgroundColor(getResources().getColor(R.color.bottomBarMore));
                           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                               Window window = getWindow();
                               window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                                       | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                               window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                               window.setStatusBarColor(getResources().getColor(R.color.bottomBarMoreDark));
                           }
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
        if (myFragment != fragment ) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(myFragment)
                        .add(R.id.main_content, fragment).show(fragment).commit();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    /*back键点击的监听实现再按一次退出程序*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.category_edit:
                showPopWindow();
        }
        return true;
    }

    private void showPopWindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View view = inflater.inflate(R.layout.popup_window, null);


        // 下面是两种方法得到宽度和高度

        window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setTouchable(true);
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色，16进制，前两位表示透明度
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        window.setBackgroundDrawable(dw);

//        //popupWindow 内部控件的初始化
        selectRecycler = (RecyclerView) window.getContentView().findViewById(R.id.select_recycler);
        unSelectRecycler = (RecyclerView) window.getContentView().findViewById(R.id.unselect_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        GridLayoutManager gridLayoutManagerOne = new GridLayoutManager(getApplicationContext(),3);
        selectRecycler.setLayoutManager(gridLayoutManager);
        unSelectRecycler.setLayoutManager(gridLayoutManagerOne);
        selectAdapter = new SelectAdapter(selectListName,selectListType,getApplicationContext());
        unSelectAdapter = new UnselectAdapter(unselectListName,unselectListType,getApplicationContext());
        //适配器相关联
        selectAdapter.setUnselectAdapter(unSelectAdapter);
        unSelectAdapter.setSelectAdapter(selectAdapter);
        selectRecycler.setAdapter(selectAdapter);
        unSelectRecycler.setAdapter(unSelectAdapter);
        //点击返回退出popupwindow
        popupWindowBack = (ImageView) window.getContentView().findViewById(R.id.window_back);
        popupWindowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        // 设置popWindow的点击背景变暗和消失时的监听事件
        window.showAtLocation(toolbar, Gravity.BOTTOM,0,0);
        myWindow = getWindow();
        WindowManager.LayoutParams params = myWindow.getAttributes();
        params.alpha = 0.5f;
        myWindow.setAttributes(params);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = myWindow.getAttributes();
                params.alpha = 1.0f;
                myWindow.setAttributes(params);
            }
        });
    }

    /**
     * 用户关注的图片分类初始化
     */
    public void setCategory(){
//      获取所有的图片类别，从1开始加载是因为1是首页要去掉
        String[] categoryName = getResources().getStringArray(R.array.title_array_all);
        String[] categoryType = getResources().getStringArray(R.array.type_array_all);
        unselectListName = new ArrayList<>();
        unselectListType = new ArrayList<>();
        for (int i=1;i<categoryName.length;i++){
           unselectListName.add(categoryName[i]);
            unselectListType.add(categoryType[i]);

        }
//      添加数据库存在的用户已选择的日报
        List<Category> list = DataSupport.findAll(Category.class);
        selectListName = new ArrayList<>();
        selectListType = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            selectListName.add(list.get(i).getCategoryName());
            selectListType.add(list.get(i).getGetCategoryType());
        }
//      去掉用户已经选择的图片分类
        for(int i=0;i<unselectListName.size();i++){
            for (String index:selectListName) {
                if(unselectListName.get(i).equals(index)){
                    unselectListName.remove(unselectListName.get(i));
                    unselectListType.remove(unselectListType.get(i));
                }

            }
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
