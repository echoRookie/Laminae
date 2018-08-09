package com.example.rookie.laminae.follow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rookie.laminae.api.FollowingAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.httputils.RetrofitClient;
import com.example.rookie.laminae.main.home.ScrollListener;
import com.example.rookie.laminae.user.UserLike.UserLikeAdapter;
import com.example.rookie.laminae.user.UserPins.UserPinsBean;
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

public class FollowPinsFragment extends Fragment {
    private RecyclerView recyclerView;
    private UserLikeAdapter myAdapter;
    private GridLayoutManager gridLayoutManager;
    private int lastPinsId;//上次请求的最后一张图片的id
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_base,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.base_recyclerView);
         gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.addOnScrollListener(new ScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore() {
                getScrollHttp();
            }
        });
        getPinsHttps();
        return view;
    }

    private void getPinsHttps() {
        RetrofitClient re = RetrofitClient.getInstance();
        FollowingAPI followAPI = re.createService(FollowingAPI.class);
        Observable<UserPinsBean> observable = followAPI.httpsMyFollowingPinsRx((String) SPUtils.get(getContext(), Constant.USERAUthorization, Base64.mClientInto),20);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserPinsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserPinsBean value) {
//                      保存图片id，下次联网时使用
                        myAdapter = new UserLikeAdapter(value.getPins(),getContext());
                        recyclerView.setLayoutManager(gridLayoutManager);
                        recyclerView.setAdapter(myAdapter);
                        int size = value.getPins().size();
                        lastPinsId = value.getPins().get(size-1).getPin_id();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
//    后续滑动联网
    private void getScrollHttp(){
        RetrofitClient re = RetrofitClient.getInstance();
        FollowingAPI followAPI = re.createService(FollowingAPI.class);
        Observable<UserPinsBean> observable = followAPI.httpsMyFollowingPinsMaxRx((String) SPUtils.get(getContext(), Constant.USERAUthorization, Base64.mClientInto),lastPinsId,20);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserPinsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserPinsBean value) {
//                      保存图片id，下次联网时使用,添加数据
                        for (int i = 0;i <value.getPins().size();i++){
                            myAdapter.getMyLikes().add(value.getPins().get(i));
                            if(i == value.getPins().size() - 1){
                                lastPinsId = value.getPins().get(i).getPin_id();
                            }
                        }
                        myAdapter.notifyDataSetChanged();

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
