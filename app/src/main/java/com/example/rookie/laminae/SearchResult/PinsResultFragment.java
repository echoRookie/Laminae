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
import com.example.rookie.laminae.search.SearchImageBean;
import com.example.rookie.laminae.httpUtils.RetrofitClient;
import com.example.rookie.laminae.user.UserLike.UserLikeAdapter;
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

public class PinsResultFragment extends Fragment {
    private RecyclerView recyclerView;
    private static final String SEARCHKEY = Constant.SEARCHKEY;
    private UserLikeAdapter myAdapter;
    private String searchKey;//搜索关键字
    private int index;//联网开始页
    //    返回实例本身
    public static PinsResultFragment newInstance(String searchKey) {
        PinsResultFragment pinsResultFragment = new PinsResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SEARCHKEY, searchKey);
        pinsResultFragment.setArguments(bundle);
        return pinsResultFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_base,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.base_recyclerView);
        Bundle bundle = getArguments();
        searchKey = bundle.getString(SEARCHKEY);
        Log.d("Pins", "onCreateView: "+searchKey);
        getPinsHttps();
        return view;
    }
// 联网获取搜索结果
    public void getPinsHttps(){
        RetrofitClient client = RetrofitClient.getInstance();
        SearchAPI searchAPI = client.createService(SearchAPI.class);
        Observable<SearchImageBean> observable = searchAPI.httpsImageSearchRx(Base64.mClientInto,searchKey,index,20);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchImageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchImageBean value) {
                        Log.d("pinss", "onNext: "+value.getPins().size());
                        myAdapter = new UserLikeAdapter(value.getPins(),getContext());
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                        recyclerView.setLayoutManager(gridLayoutManager);
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
