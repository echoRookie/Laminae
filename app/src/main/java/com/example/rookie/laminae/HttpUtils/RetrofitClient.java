package com.example.rookie.laminae.httpUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rookie on 2018/3/20.
 * 创建 retrofit 网络连接 ，单例模式
 */

public class RetrofitClient {
    public static String baseUrl = "https://api.huaban.com/";
    private Retrofit retrofit;
    private static RetrofitClient retrofitClient = null;
    /*
    * 私有构造函数，初始化retrofit
    * */
    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public static RetrofitClient getInstance(){
        if(retrofitClient == null)
            retrofitClient = new RetrofitClient();
        return retrofitClient;

    }
    /*
    * 根据传入的类创建网络请求
    * */
    public <T> T createService (Class<T> serviceClass){
        return retrofit.create(serviceClass);
    }

}
