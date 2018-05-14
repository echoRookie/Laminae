package com.example.rookie.laminae.user;

import android.util.Log;

import com.example.rookie.laminae.api.UserAPI;
import com.example.rookie.laminae.base.CallBack;
import com.example.rookie.laminae.httputils.RetrofitClient;
import com.example.rookie.laminae.login.UserBean;
import com.example.rookie.laminae.util.Base64;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rookie on 2018/4/21.
 * 对用户信息进行联网查询操作
 */

public class UserModel {
    /**
     * 根据Id查询用户信息
     * @param userId
     * @param callBack
     */
    public void getUserInfoAPI(String userId, final CallBack callBack){
        RetrofitClient client = RetrofitClient.getInstance();
        UserAPI userAPI = client.createService(UserAPI.class);
        Observable<UserBean> observable = userAPI.httpsUserInfoRx(Base64.mClientInto,userId);
        observable.subscribeOn(Schedulers.computation())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBean value) {
                        callBack.onCompleted(value);
                        Log.d("UserModel", "onNext: "+value.getBoard_count());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("UserModel", "onError: "+e.toString());
                        callBack.onError();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
