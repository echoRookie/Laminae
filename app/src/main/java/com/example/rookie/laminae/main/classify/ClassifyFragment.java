package com.example.rookie.laminae.main.classify;

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

import com.bumptech.glide.Glide;
import com.example.rookie.laminae.API.TypeAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.httpUtils.RetrofitClient;
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
 * Created by rookie on 2018/4/26.
 */

public class ClassifyFragment extends Fragment {
    private List<String> myTitles;
    private List<String> myTypes;
    private RecyclerView recyclerView;
    private List<String> myKeys;
    private ClassifyAdapter myAdapter;
    private int i;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//      初始化分类列表
        String[] titles = getResources().getStringArray(R.array.title_array_all);
        String[] types = getResources().getStringArray(R.array.type_array_all);
        myTitles = new ArrayList<>();
        myTypes = new ArrayList<>();
        myKeys = new ArrayList<>();
        for (int i=0;i<titles.length;i++){
            myTitles.add(titles[i]);
            myTypes.add(types[i]);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_base,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.base_recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPinsKeys();
            }
        });
        getPinsKeys();

        return view;
    }

    /**
     * 获取每个类别的前三张图片并保存
     */
    public void getPinsKeys(){
        for (i = 0;i<myTitles.size();i++){
            RetrofitClient client = RetrofitClient.getInstance();
            TypeAPI typeAPI = client.createService(TypeAPI.class);
            Observable<UserPinsBean> observable = typeAPI.httpsTypeLimitRx(Constant.Authorization,myTypes.get(i),3);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<UserPinsBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(UserPinsBean value) {
                          for(int j = 0;j<3;j++){
                              myKeys.add(value.getPins().get(j).getFile().getKey());
                              Log.d("class", "onNext: "+myKeys.size());
                              if(myKeys.size() == (myTitles.size()*3)){
                                  myAdapter = new ClassifyAdapter(myTitles,myKeys,myTypes,getContext());
                                  GridLayoutManager manager = new GridLayoutManager(getContext(),2);
                                  recyclerView.setLayoutManager(manager);
                                  recyclerView.setAdapter(myAdapter);
                                  Log.d("class", "onNext: "+myKeys.size()+"AAAa");
                                  swipeRefreshLayout.setRefreshing(false);
                              }
                          }


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
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }
}
