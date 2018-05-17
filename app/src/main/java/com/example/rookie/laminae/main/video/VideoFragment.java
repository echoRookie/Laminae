package com.example.rookie.laminae.main.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rookie.laminae.R;
import com.example.rookie.laminae.api.VideoAPI;
import com.example.rookie.laminae.entity.Daily;
import com.example.rookie.laminae.httputils.VideoRetrofitClient;
import com.example.rookie.laminae.main.home.ScrollListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by rookie on 2018/5/15.
 */

public class VideoFragment extends Fragment {
    private VideoAdapter myAdapter;
    private RecyclerView recyclerView;
    private List<Daily.IssueList.ItemList> myList;
    private LinearLayoutManager manager;
    private String nextPageUrl;//视频下一页的联网地址
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.recyclerview_base,container,false);
        recyclerView = (RecyclerView)  view.findViewById(R.id.base_recyclerView);
        manager = new LinearLayoutManager(getContext());
        myList = new ArrayList<>();
        getVideoHttp();
//      下滑加载更多
        recyclerView.addOnScrollListener(new ScrollListener(manager) {
            @Override
            public void onLoadMore() {
                getVideoNext();
            }
        });
        return view;
    }

    /**
     * 得到视频的网络数据
     */
    public void getVideoHttp(){
        Retrofit retrofit = VideoRetrofitClient.getRetrofit();
        VideoAPI videoAPI = retrofit.create(VideoAPI.class);
        Observable<Daily> observable = videoAPI.getDaily();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Daily>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Daily value) {
//                      保存下一次刷新的网址
                        nextPageUrl = value.nextPageUrl;
//                      解析视频数组
                        for(int i=0;i<value.issueList.size();i++){
                            for (Daily.IssueList.ItemList list:value.issueList.get(i).itemList) {
                                Log.d("video未加", "onNext: "+list.type);
                                if (list.type.equals("video")){
                                    myList.add(list);
                                    Log.d("video已加", "onNext: "+list.data.title);
                                }

                            }

                        }
                        Log.d("video长度", "onNext: "+myList.size());
                        myAdapter = new VideoAdapter(myList,getContext());
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(myAdapter);


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 滑动获取下一页
     */
    public void getVideoNext(){
        Retrofit retrofit = VideoRetrofitClient.getRetrofit();
        VideoAPI videoAPI = retrofit.create(VideoAPI.class);
        Observable<Daily> observable = videoAPI.getNextDaily(nextPageUrl);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Daily>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Daily value) {
                        nextPageUrl = value.nextPageUrl;
                        //                      解析视频数组
                        for(int i=0;i<value.issueList.size();i++){
                            for (Daily.IssueList.ItemList list:value.issueList.get(i).itemList) {
                                Log.d("video未加", "onNext: "+list.type);
                                if (list.type.equals("video")){
                                    myList.add(list);
                                }

                            }

                        }
                        Log.d("video长度", "onNext: "+myList.size());

                        myAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
