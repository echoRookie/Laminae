package com.example.rookie.laminae.searchResult;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rookie.laminae.API.SearchAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.search.SearchPeopleListBean;
import com.example.rookie.laminae.httpUtils.RetrofitClient;
import com.example.rookie.laminae.util.Base64;
import com.example.rookie.laminae.util.Constant;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rookie on 2018/5/7.
 */

public class UserResultFragment extends Fragment {
    private RecyclerView recyclerView;
    private String searchKey;//搜索关键字
    private static final String SEARCHKEY = Constant.SEARCHKEY;
    private UserResultAdapter userResultAdapter;
    private int index = 1;//联网起始页
    //    返回实例本身
    public static UserResultFragment newInstance(String searchKey) {
        UserResultFragment userResultFragment = new UserResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SEARCHKEY, searchKey);
        userResultFragment.setArguments(bundle);
        return userResultFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_base,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.base_recyclerView);
        Bundle bundle = getArguments();
        searchKey = bundle.getString(SEARCHKEY);
        getUserHttp();
        return view;
    }

    /**
     * 联网请求搜索用户的数据
     */
    public void getUserHttp(){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        SearchAPI searchAPI = retrofitClient.createService(SearchAPI.class);
        Observable<SearchPeopleListBean> observable = searchAPI.httpsPeopleSearchRx(Base64.mClientInto,searchKey,index,20);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchPeopleListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchPeopleListBean value) {
                        if (value!= null) {
                            Log.d("userResult", "onNext: " + value.getUsers().size() + "neirong" + value.getUsers().get(1).getFollower_count());
                            userResultAdapter = new UserResultAdapter(value.getUsers(), getContext());
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            recyclerView.setAdapter(userResultAdapter);
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
}
