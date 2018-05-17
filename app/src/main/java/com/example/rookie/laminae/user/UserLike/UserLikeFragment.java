package com.example.rookie.laminae.user.UserLike;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rookie.laminae.api.UserAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.httputils.RetrofitClient;
import com.example.rookie.laminae.user.UserPins.UserPinsBean;
import com.example.rookie.laminae.util.Base64;
import com.example.rookie.laminae.util.Constant;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rookie on 2018/4/22.
 */

public class UserLikeFragment extends Fragment {
    private String userId;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private UserLikeAdapter myAdapter;
    public static  UserLikeFragment newINstance(String userId){
        UserLikeFragment userLikeFragment = new UserLikeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.USERID,userId);
        userLikeFragment.setArguments(bundle);
        return  userLikeFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment_baseview, container, false);
        Bundle bundle = getArguments();
        userId = bundle.getString(Constant.USERID);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        getLikesFirst();
        return view;
    }
    public void getLikesFirst(){
        RetrofitClient client = RetrofitClient.getInstance();
        Observable<UserPinsBean> observable = client.createService(UserAPI.class).httpsUserLikePinsRx(Base64.mClientInto,userId,20);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserPinsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserPinsBean value) {
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
