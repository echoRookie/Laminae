package com.example.rookie.laminae.login;

import com.example.rookie.laminae.API.TokenAPI;
import com.example.rookie.laminae.base.CallBack;
import com.example.rookie.laminae.httpUtils.RetrofitClient;
import com.example.rookie.laminae.util.Constant;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by rookie on 2018/4/11.
 * 用于登录的model层
 */

public class LoginModel  {
    /**
     * 用于进行用户登录
     * @param username 用户名
     * @param password 密码
     * @param callBack  回调接口
     */
    void getLoginApi(String username, String password, final CallBack callBack){
        RetrofitClient retrofit = RetrofitClient.getInstance();
        Observable<TokenBean> oobservable = retrofit.createService(TokenAPI.class).httpsGetTokenRx(Constant.Authorization, Constant.PASSWORD, username, password);
        oobservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TokenBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TokenBean value) {
                        callBack.onSuccess();

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError();

                    }

                    @Override
                    public void onComplete() {
                        callBack.onCompleted();

                    }
                });


    };
}
