package com.example.rookie.laminae.API;

import com.example.rookie.laminae.imageDetial.PinsDetialBean;
import com.example.rookie.laminae.user.UserPins.UserPinsBean;
import com.example.rookie.laminae.util.Constant;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rookie on 2018/4/26.
 */

public interface ImageDetailAPI {
    //https://api.huaban.com/pins/663478425
    //根据图片id获取详情
    @GET("pins/{pinsId}")
    Observable<PinsDetialBean> httpsPinsDetailRx(@Header(Constant.Authorization) String authorization, @Path("pinsId") String pinsId);

    //https//api.huaban.com/pins/654197326/recommend/?page=1&limit=40
    //获取某个图片的推荐图片列表
    @GET("pins/{pinsId}/recommend/")
    Observable<List<UserPinsBean.UserPinsItem>> httpPinsRecommendRx(@Header(Constant.Authorization) String authorization, @Path("pinsId") String pinsId, @Query("page") int page, @Query("limit") int limit);
}
