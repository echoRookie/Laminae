package com.example.rookie.laminae.API;

import com.example.rookie.laminae.user.UserPins.UserPinsBean;
import com.example.rookie.laminae.util.Constant;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rookie on 2018/4/25.
 */

public interface TypeAPI {
    //https//api.huaban.com/favorite/food_drink?limit=20
    // 模板类型
    @GET("favorite/{type}")
    Observable<UserPinsBean> httpsTypeLimitRx(@Header(Constant.Authorization) String authorization, @Path("type") String type, @Query("limit") int limit);

    //https//api.huaban.com/favorite/food_drink?max=5445324325&limit=20
    //模板类型 的后续联网 max
    @GET("favorite/{type}")
    Observable<UserPinsBean> httpsTypeMaxLimitRx(@Header(Constant.Authorization) String authorization, @Path("type") String type, @Query("max") int max, @Query("limit") int limit);
    @GET("favorite/{type}/board")
    Observable<ResponseBody> httpsTypeLRx(@Header(Constant.Authorization) String authorization, @Path("type") String type, @Query("limit") int limit);
}
