package com.example.rookie.laminae.API;

import com.example.rookie.laminae.entity.FollowBoardListBean;
import com.example.rookie.laminae.user.UserPins.UserPinsBean;
import com.example.rookie.laminae.util.Constant;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by rookie on 2018/5/12.
 */

public interface FollowingAPI {
    //https://api.huaban.com/following?limit=40
    //我的关注图片  需要 报头 bearer getAccess_token
    @GET("following")
    Observable<UserPinsBean> httpsMyFollowingPinsRx(@Header(Constant.Authorization) String authorization, @Query("limit") int limit);

    //https://api.huaban.com/following?limit=40&max=670619456
    //我的关注图片的 后续滑动联网
    @GET("following")
    Observable<UserPinsBean> httpsMyFollowingPinsMaxRx(@Header(Constant.Authorization) String authorization, @Query("max") int max, @Query("limit") int limit);

    //https://api.huaban.com/boards/following?page=1&per_page=20
    //我的关注画板
    @GET("boards/following")
    Observable<FollowBoardListBean> httpsMyFollowingBoardRx(@Header(Constant.Authorization) String authorization, @Query("page") int page, @Query("per_page") int per_page);
    @GET("users/following")
    Observable<ResponseBody> httpsMyFollowingUserRx(@Header(Constant.Authorization) String authorization, @Query("page") int page, @Query("per_page") int per_page);
}
