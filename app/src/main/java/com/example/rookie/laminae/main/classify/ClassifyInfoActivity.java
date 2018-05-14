package com.example.rookie.laminae.main.classify;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.rookie.laminae.api.TypeAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.httputils.RetrofitClient;
import com.example.rookie.laminae.main.home.ScrollListener;
import com.example.rookie.laminae.user.UserLike.UserLikeAdapter;
import com.example.rookie.laminae.user.UserPins.UserPinsBean;
import com.example.rookie.laminae.util.Constant;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClassifyInfoActivity extends AppCompatActivity {
    private List<UserPinsBean.UserPinsItem> myLins;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private UserLikeAdapter myAdapter;
    private GridLayoutManager manager;
    private Toolbar toolbar;
    //  记录最后一次联网的图片的id
    private int lastPinsId;
    private String typeTitle;//图片类别标题
    private String typeKey;//图片类别key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_fragment_baseview);
        typeTitle = getIntent().getStringExtra(Constant.TYPETITLE);
        typeKey = getIntent().getStringExtra(Constant.TYPEKEY);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle(typeTitle);
        setSupportActionBar(toolbar);
        /*显示toolbar自带返回键设置标题*/
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        manager= new GridLayoutManager(getApplicationContext(),2);
        getFirstHttps();
        recyclerView.addOnScrollListener(new ScrollListener(manager) {
            @Override
            public void onLoadMore() {
                getNextHttps();
            }
        });
    }

    /**
     * 第一次联网加载
     */
    public void getFirstHttps (){
        RetrofitClient client = RetrofitClient.getInstance();
        TypeAPI typeAPI = client.createService(TypeAPI.class);
        Observable<UserPinsBean> observable = typeAPI.httpsTypeLimitRx(Constant.Authorization,typeKey,20);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserPinsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserPinsBean value) {
                        Log.d("HomeFragment", "onNext: "+value.getPins().size());
                        myLins = new ArrayList<UserPinsBean.UserPinsItem>();
                        for(int i=0;i<value.getPins().size();i++){
                            myLins.add(value.getPins().get(i));
                        }
                        lastPinsId = myLins.get(myLins.size()-1).getPin_id();
                        myAdapter = new UserLikeAdapter(myLins,getApplicationContext());
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(myAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("mainactivity", "onError: "+e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 用户接下来的滑动联网
     */
    public void getNextHttps(){
        RetrofitClient client = RetrofitClient.getInstance();
        TypeAPI typeAPI = client.createService(TypeAPI.class);
        Observable<UserPinsBean> observable = typeAPI.httpsTypeMaxLimitRx(Constant.Authorization,typeKey,lastPinsId,20);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserPinsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserPinsBean value) {
                        if (myLins!=null){
                            for (int i =0;i<value.getPins().size();i++){
                                myLins.add(value.getPins().get(i));
                            }
                            myAdapter.notifyDataSetChanged();
                            lastPinsId = value.getPins().get(value.getPins().size()-1).getPin_id();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
}
