package com.example.rookie.laminae.api;

import com.example.rookie.laminae.entity.BoardListInfoBean;
import com.example.rookie.laminae.login.UserBean;
import com.example.rookie.laminae.user.UserBoard.UserBoardBean;
import com.example.rookie.laminae.user.UserPins.UserPinsBean;
import com.example.rookie.laminae.util.Constant;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rookie on 2018/4/14.
 */

public interface UserAPI {
    //获取登录用户信息
    @GET("users/me")
    Observable<UserBean> httpsUserRx(@Header(Constant.Authorization) String authorization);

    //https://api.huaban.com/users/15246080
    //获取个人信息
    @GET("users/{userId}")
    Observable<UserBean> httpsUserInfoRx(@Header(Constant.Authorization) String authorization, @Path("userId") String pinsId);

    //获取我的画板集合信息 不需要显示需要保存
    //https://api.huaban.com/last_boards/?extra=recommend_tags
    @GET("last_boards/")
    Observable<BoardListInfoBean> httpsBoardListInfo(@Header(Constant.Authorization) String authorization, @Query("extra") String extra);

    //用户画板信息
    @GET("users/{userId}/boards")
    Observable<UserBoardBean> httpsUserBoardRx(@Header(Constant.Authorization) String authorization, @Path("userId") String pinsId, @Query("limit") int limit);

    //https://api.huaban.com/users/16211815/boards?limit=20&max=18375118
    @GET("users/{userId}/boards")
    Observable<UserBoardBean> httpsUserBoardMaxRx(@Header(Constant.Authorization) String authorization, @Path("userId") String pinsId, @Query("max") int max, @Query("limit") int limit);

    //用户的采集
    @GET("users/{userId}/pins")
    Observable<UserPinsBean> httpsUserPinsRx(@Header(Constant.Authorization) String authorization, @Path("userId") String pinsId, @Query("limit") int limit);

    //https://api.huaban.com/users/188174/pins?limit=40&max=19532400
    //后续滑动联网
    @GET("users/{userId}/pins")
    Observable<UserPinsBean> httpsUserPinsMaxRx(@Header(Constant.Authorization) String authorization, @Path("userId") String pinsId, @Query("max") int max, @Query("limit") int limit);

    //用户的喜欢
    @GET("users/{userId}/likes")
    Observable<UserPinsBean> httpsUserLikePinsRx(@Header(Constant.Authorization) String authorization, @Path("userId") String pinsId, @Query("limit") int limit);

    //https://api.huaban.com/users/743988/likes?limit=40&max=4338219
    //用户喜欢的后续联网
    @GET("users/{userId}/likes")
    Observable<UserPinsBean> httpsUserLikePinsMaxRx(@Header(Constant.Authorization) String authorization, @Path("userId") String pinsId, @Query("max") int max, @Query("limit") int limit);

    //https://api.huaban.com/users/188174/likes?limit=40
    /*
    //获取我的画板集合信息 不需要显示需要保存
    //https://api.huaban.com/last_boards/?extra=recommend_tags
    @GET("last_boards/")
    Observable<BoardListInfoBean> httpsBoardListInfo(@Header(Constant.Authorization) String authorization, @Query("extra") String extra);

    //https://api.huaban.com/users/15246080/boards?limit=20
    //用户画板信息
    @GET("users/{userId}/boards")
    Observable<UserBoardListBean> httpsUserBoardRx(@Header(Constant.Authorization) String authorization, @Path("userId") String pinsId, @Query("limit") int limit);

    //https://api.huaban.com/users/16211815/boards?limit=20&max=18375118
    @GET("users/{userId}/boards")
    Observable<UserBoardListBean> httpsUserBoardMaxRx(@Header(Constant.Authorization) String authorization, @Path("userId") String pinsId, @Query("max") int max, @Query("limit") int limit);

    //https://api.huaban.com/users/188174/pins?limit=40
    //用户的采集
    @GET("users/{userId}/pins")
    Observable<ListPinsBean> httpsUserPinsRx(@Header(Constant.Authorization) String authorization, @Path("userId") String pinsId, @Query("limit") int limit);

    //https://api.huaban.com/users/188174/pins?limit=40&max=19532400
    //后续滑动联网
    @GET("users/{userId}/pins")
    Observable<ListPinsBean> httpsUserPinsMaxRx(@Header(Constant.Authorization) String authorization, @Path("userId") String pinsId, @Query("max") int max, @Query("limit") int limit);

    //https://api.huaban.com/users/188174/likes?limit=40
    //用户的喜欢
    @GET("users/{userId}/likes")
    Observable<ListPinsBean> httpsUserLikePinsRx(@Header(Constant.Authorization) String authorization, @Path("userId") String pinsId, @Query("limit") int limit);

    //https://api.huaban.com/users/743988/likes?limit=40&max=4338219
    //用户喜欢的后续联网
    @GET("users/{userId}/likes")
    Observable<ListPinsBean> httpsUserLikePinsMaxRx(@Header(Constant.Authorization) String authorization, @Path("userId") String pinsId, @Query("max") int max, @Query("limit") int limit);
    */
    //用户的关注
    @GET("users/{userId}/following/")
    Observable<ResponseBody> httpsUserFollowsPinsRx(@Header(Constant.Authorization) String authorization, @Path("userId") String pinsId, @Query("limit") int limit);
}
