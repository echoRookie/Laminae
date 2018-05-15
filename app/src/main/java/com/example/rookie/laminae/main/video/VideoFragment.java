package com.example.rookie.laminae.main.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rookie.laminae.R;
import com.example.rookie.laminae.api.VideoAPI;
import com.example.rookie.laminae.entity.Daily;
import com.example.rookie.laminae.httputils.VideoRetrofitClient;

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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.recyclerview_base,container,false);
        recyclerView = (RecyclerView)  view.findViewById(R.id.base_recyclerView);
        getVideoHttp();
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

                        myAdapter = new VideoAdapter(value.issueList.get(0).itemList,getContext());
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(linearLayoutManager);
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
}
