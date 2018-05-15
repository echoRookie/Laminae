package com.example.rookie.laminae.httputils;

import com.example.rookie.laminae.util.Constant;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rookie on 2018/5/15.
 */

public class VideoRetrofitClient {
    private static Retrofit retrofit;
    private static final String URL = "http://baobab.kaiyanapp.com/api/";
    public static Retrofit getRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
