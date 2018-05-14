package com.example.rookie.laminae.login;

import android.util.Log;

import com.example.rookie.laminae.api.TokenAPI;
import com.example.rookie.laminae.api.UserAPI;
import com.example.rookie.laminae.base.BaseApplication;
import com.example.rookie.laminae.base.CallBack;
import com.example.rookie.laminae.httputils.RetrofitClient;
import com.example.rookie.laminae.util.Base64;
import com.example.rookie.laminae.util.Constant;
import com.example.rookie.laminae.util.SPUtils;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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
    void getLoginApi(final String username, String password, final CallBack callBack){
        final RetrofitClient client = RetrofitClient.getInstance();
        TokenAPI tokenApi = client.createService(TokenAPI.class);
        tokenApi.httpsGetTokenRx(Base64.mClientInto, Constant.PASSWORD, username, password)
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<TokenBean, ObservableSource<UserBean>>() {
                    @Override
                    public ObservableSource<UserBean> apply(TokenBean tokenBean) throws Exception {
                        Log.d("loginModel", "apply: "+tokenBean.toString());
                        String mAuthorization = tokenBean.getToken_type() + " " + tokenBean.getAccess_token();
                        if(mAuthorization!=null){
                            SPUtils.putApply(BaseApplication.getContext(),Constant.USERAUthorization,mAuthorization);
                        }
                        return client.createService(UserAPI.class).httpsUserRx(mAuthorization);
                    }
                })
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        if(userBean ==null){
                            callBack.onError();

                        }
                        else{
                        Log.d("loginModel", "onNext: "+userBean.getUsername()+userBean.getEmail());
                        callBack.onCompleted(userBean);}
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("loginModel", "onError: "+e.toString());
                        callBack.onFailure();
                    }

                    @Override
                    public void onComplete() {

                    }
                });



    };
}
