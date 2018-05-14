package com.example.rookie.laminae.main.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rookie.laminae.R;
import com.example.rookie.laminae.api.NewsAPI;
import com.example.rookie.laminae.entity.NewsDataDetial;
import com.example.rookie.laminae.entity.NewsList;
import com.example.rookie.laminae.httputils.NewsRetrofitClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by rookie on 2018/5/14.
 */

public class NewsListFragment extends Fragment {
    private RecyclerView recyclerView;
    private static final String CATEGORY ="categoryCode";
    private String categoryCode;
    private List<NewsDataDetial> list;
    private NewsListAdapter myAdapter;
    public static NewsListFragment newInstance(String id){
        NewsListFragment newsListFragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY,id);
        newsListFragment.setArguments(bundle);
        return newsListFragment;

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_base,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.base_recyclerView);
        Bundle bundle = new Bundle();
        categoryCode = bundle.getString(CATEGORY);
        getNewsList();
        return view;
    }

    /**
     * 得到新闻数据并解析
     */
    public void getNewsList(){
        Retrofit retrofit = NewsRetrofitClient.getRe();
        NewsAPI newsAPI = retrofit.create(NewsAPI.class);
        Observable<NewsList> observable= newsAPI.getNewsList(categoryCode,System.currentTimeMillis()/1000,System.currentTimeMillis()/1000);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsList value) {
                        list = new ArrayList<NewsDataDetial>();
                        for (int i=0;i<value.data.size();i++){
//                          去掉数据中的\，然后才能进行数据的解析
                            NewsDataDetial newsDataDetail = new Gson().fromJson(value.data.get(i).content,NewsDataDetial.class);
                            list.add(newsDataDetail);

                        }
                        myAdapter = new NewsListAdapter(list,getContext());
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
