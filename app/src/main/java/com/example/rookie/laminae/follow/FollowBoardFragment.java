package com.example.rookie.laminae.follow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rookie.laminae.API.FollowingAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.entity.BoardListInfoBean;
import com.example.rookie.laminae.entity.FollowBoardListBean;
import com.example.rookie.laminae.httpUtils.RetrofitClient;
import com.example.rookie.laminae.searchResult.BoardResultAdapter;
import com.example.rookie.laminae.util.Base64;
import com.example.rookie.laminae.util.Constant;
import com.example.rookie.laminae.util.SPUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rookie on 2018/5/13.
 */

public class FollowBoardFragment extends Fragment {
    private RecyclerView recyclerView;
    private FollowBoardAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_base,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.base_recyclerView);
        getBoardHttp();
        return view;
    }

    private void getBoardHttp() {
        RetrofitClient re = RetrofitClient.getInstance();
        FollowingAPI followAPI = re.createService(FollowingAPI.class);
        Observable<FollowBoardListBean> observable= followAPI.httpsMyFollowingBoardRx((String) SPUtils.get(getContext(), Constant.USERAUthorization, Base64.mClientInto),1,20);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FollowBoardListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FollowBoardListBean value) {
                        myAdapter = new FollowBoardAdapter(value.getBoards(),getContext());
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
