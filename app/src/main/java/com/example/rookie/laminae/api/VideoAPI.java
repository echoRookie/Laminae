package com.example.rookie.laminae.api;

import com.example.rookie.laminae.entity.Daily;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rookie on 2018/5/15.
 */

public interface VideoAPI {
    @GET("v2/feed?num=2")
    Observable<Daily> getDaily(@Query("date") long date);

    @GET("v2/feed?num=2")
    Observable<Daily> getDaily();
}
