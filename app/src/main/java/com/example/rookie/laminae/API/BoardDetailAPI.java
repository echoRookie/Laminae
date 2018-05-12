package com.example.rookie.laminae.API;

import com.example.rookie.laminae.entity.BoardDetailBean;
import com.example.rookie.laminae.entity.ListPinsBean;
import com.example.rookie.laminae.user.UserPins.UserPinsBean;
import com.example.rookie.laminae.util.Constant;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rookie on 2018/5/9.
 */

public interface BoardDetailAPI {
    //https//api.huaban.com/boards/3514299
    //获取画板的详情
    @GET("boards/{boardId}")
    Observable<BoardDetailBean> httpsBoardDetailRx(@Header(Constant.Authorization) String authorization, @Path("boardId") String boardId);

    //https//api.huaban.com/boards/19196160/pins?limit=40
    //获取画板的图片集合
    @GET("boards/{boardId}/pins")
    Observable<ListPinsBean> httpsBoardPinsRx(@Header(Constant.Authorization) String authorization, @Path("boardId") String boardId, @Query("limit") int limit);

    //https//api.huaban.com/boards/19196160/pins?limit=40&max=508414882
    //获取画板的图片集合 根据上一个图片的id继续加载
    @GET("boards/{boardId}/pins")
    Observable<ListPinsBean> httpsBoardPinsMaxRx(@Header(Constant.Authorization) String authorization, @Path("boardId") String boardId, @Query("max") int max, @Query("limit") int limit);
}
