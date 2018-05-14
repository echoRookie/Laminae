package com.example.rookie.laminae.user.UserBoard;

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
 * Created by rookie on 2018/4/21.
 */

public class UserBoardFragment extends Fragment {
    private static final String USERID = Constant.USERID;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    //    返回实例本身
    public static UserBoardFragment newInstance(String userId) {
        UserBoardFragment userBoardFragment = new UserBoardFragment();
        Bundle bundle = new Bundle();
        bundle.putString(USERID, userId);
        userBoardFragment.setArguments(bundle);
        return userBoardFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment_baseview, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        getBoardInfoFirst();
        return view;
    }

    /**
     * 第一次联网请求
     */
    public void getBoardInfoFirst() {
        RetrofitClient client = RetrofitClient.getInstance();
        UserAPI userAPi = client.createService(UserAPI.class);
        Observable<UserBoardBean> observable = userAPi.httpsUserBoardRx(Base64.mClientInto, "22282493", 20);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBoardBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBoardBean value) {
//                        Log.d("UserBoardFragment", "onNext: " + value.getBoards().size());
                        UserBoardAdapter myAdapter = new UserBoardAdapter(value.getBoards(), getContext(),getFragmentManager());
                        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(myAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("UserBoardFragment", "onError: " + e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
