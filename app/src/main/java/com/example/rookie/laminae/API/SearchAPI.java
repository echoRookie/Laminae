package com.example.rookie.laminae.api;

import com.example.rookie.laminae.search.SearchBoardListBean;
import com.example.rookie.laminae.search.SearchHintBean;
import com.example.rookie.laminae.search.SearchImageBean;
import com.example.rookie.laminae.search.SearchPeopleListBean;
import com.example.rookie.laminae.util.Constant;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by rookie on 2018/5/4.
 */

public interface SearchAPI {
    //https//api.huaban.com/search/hint?q=%E4%BA%BA
    //搜索关键字 提示
    @GET("search/hint")
    Observable<SearchHintBean> httpsSearHintBean(@Header(Constant.Authorization) String authorization, @Query("q") String key);

    //https//api.huaban.com/search/?q=%E7%BE%8E%E9%A3%9F&page=1&per_page=2
    //图片搜索 返回结果跟模板类型差不多
    @GET("search/")
    Observable<SearchImageBean> httpsImageSearchRx(@Header(Constant.Authorization) String authorization, @Query("q") String key, @Query("page") int page, @Query("per_page") int per_page);

    //https//api.huaban.com/search/boards/?q=%E7%BE%8E%E9%A3%9F&page=1&per_page=1
    //画板搜索
    @GET("search/boards/")
    Observable<SearchBoardListBean> httpsBoardSearchRx(@Header(Constant.Authorization) String authorization, @Query("q") String key, @Query("page") int page, @Query("per_page") int per_page);

    //https//api.huaban.com/search/people/?q=%E7%BE%8E%E9%A3%9F&page=1&per_page=2
    //用户搜索
    @GET("search/people/")
    Observable<SearchPeopleListBean> httpsPeopleSearchRx(@Header(Constant.Authorization) String authorization, @Query("q") String key, @Query("page") int page, @Query("per_page") int per_page);
}
