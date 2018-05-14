package com.example.rookie.laminae.user.UserPins;

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

import com.example.rookie.laminae.api.UserAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.httputils.RetrofitClient;
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

public class UserPinsFragment extends Fragment {
    private static final String USERID = Constant.USERID;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private UserPinsAdapter myAdapter;

    //    返回实例本身
    public static UserPinsFragment newInstance(String userId) {
        UserPinsFragment userPinsFragment = new UserPinsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(USERID, userId);
        userPinsFragment.setArguments(bundle);
        return userPinsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment_baseview,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        getPinsFirst();
        return  view;
    }
    public void getPinsFirst(){
        RetrofitClient client = RetrofitClient.getInstance();
        Observable<UserPinsBean> observable = client.createService(UserAPI.class).httpsUserPinsRx(Base64.mClientInto,"22282493",20);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserPinsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserPinsBean value) {
                        Log.d("userlogin", "onNext: "+value.getPins().size());
                        myAdapter = new UserPinsAdapter(value.getPins(),getContext());
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
