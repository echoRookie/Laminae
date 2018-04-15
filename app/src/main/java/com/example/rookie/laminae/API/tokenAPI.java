package com.example.rookie.laminae.API;

import android.media.session.MediaSession;

import com.example.rookie.laminae.login.TokenBean;
import com.example.rookie.laminae.util.Constant;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by rookie on 2018/4/11.
 * 用于登录时进行 Oauth2.0认证
 */

public interface TokenAPI {
    //https 获取token接口 OAuth 2.0密码模式
    //Authorization 报头一个固定的值 内容 grant_type=password&password=密码&username=账号
    //传入用户名和密码
    @FormUrlEncoded
    @POST("oauth/access_token/")
    Observable<TokenBean> httpsGetTokenRx(@Header(Constant.Authorization) String authorization, @Field("grant_type") String grant,
                                          @Field("username") String username, @Field("password") String password);

    //刷新token接口
    @FormUrlEncoded
    @POST("oauth/access_token/")
    Observable<TokenBean> httpsRefreshTokenRx(@Header(Constant.Authorization) String authorization, @Field("grant_type") String grant,
                                                       @Field("refresh_token") String username);
}
