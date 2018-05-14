package com.example.rookie.laminae.main.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rookie.laminae.api.TypeAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.httputils.RetrofitClient;
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

/**
 * Created by rookie on 2018/4/25.
 * 目前先使用用户喜欢界面的布局和适配器
 * 使用UserLikeAdapter
 */

public class HomeFragment extends Fragment {
    private List<UserPinsBean.UserPinsItem> myLins;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private UserLikeAdapter myAdapter;
    private GridLayoutManager manager;
//  记录最后一次联网的图片的id
    private int lastPinsId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.user_fragment_baseview,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        manager= new GridLayoutManager(getContext(),2);
        getFirstHttps();
        recyclerView.addOnScrollListener(new ScrollListener(manager) {
            @Override
            public void onLoadMore() {
                getNextHttps();
            }
        });
        return view;
    }

    /**
     * 第一次联网加载
     */
    public void getFirstHttps (){
        RetrofitClient client = RetrofitClient.getInstance();
        TypeAPI typeAPI = client.createService(TypeAPI.class);
        Observable<UserPinsBean> observable = typeAPI.httpsTypeLimitRx(Constant.Authorization,"all",20);
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
                        myAdapter = new UserLikeAdapter(myLins,getContext());
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
        Observable<UserPinsBean> observable = typeAPI.httpsTypeMaxLimitRx(Constant.Authorization,"all",lastPinsId,20);
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

    public List<UserPinsBean.UserPinsItem> getMyLins() {
        return myLins;
    }

    public void setMyLins(List<UserPinsBean.UserPinsItem> myLins) {
        this.myLins = myLins;
    }
}
