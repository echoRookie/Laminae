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

import com.example.rookie.laminae.api.TypeAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.db.Category;
import com.example.rookie.laminae.db.SearchHistory;
import com.example.rookie.laminae.httputils.RetrofitClient;
import com.example.rookie.laminae.user.UserPins.UserPinsBean;
import com.example.rookie.laminae.util.Constant;

import org.litepal.crud.DataSupport;

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
    private List<Category> categoryList; //图片分类列表
    private int i;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//      初始化分类列表,从数据库中获取
        categoryList = DataSupport.findAll(Category.class);
        myTitles = new ArrayList<>();
        myTypes = new ArrayList<>();
        myKeys = new ArrayList<>();
        for (int i=0;i<categoryList.size();i++){
            myTitles.add(categoryList.get(i).getCategoryName());
            myTypes.add(categoryList.get(i).getGetCategoryType());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_base,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.base_recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
//       刷新事件
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//              首先清空图片分类列表
                categoryList.clear();
                myTitles.clear();
                myTypes.clear();
//               重新从数据库获取数据
                categoryList = DataSupport.findAll(Category.class);
                for (int i=0;i<categoryList.size();i++){
                    myTitles.add(categoryList.get(i).getCategoryName());
                    myTypes.add(categoryList.get(i).getGetCategoryType());
                }
                getPinsKeys();
//                通知适配器更新数据
                myAdapter.notifyDataSetChanged();
//                刷新完成
                swipeRefreshLayout.setRefreshing(false);
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
