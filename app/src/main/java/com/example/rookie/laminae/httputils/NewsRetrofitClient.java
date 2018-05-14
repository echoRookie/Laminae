package com.example.rookie.laminae.httputils;

import com.example.rookie.laminae.util.Constant;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rookie on 2018/5/14.
 */

public class NewsRetrofitClient {
    private static Retrofit retrofit;
    public static Retrofit getRe(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
}
}
