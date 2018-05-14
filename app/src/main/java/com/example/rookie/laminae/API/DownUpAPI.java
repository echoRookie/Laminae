package com.example.rookie.laminae.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 图片的加载接口
 */

public interface DownUpAPI {
    @GET("http://img.hb.aicdn.com/{pinId}")
    Observable<ResponseBody> httpDownImage(@Path("pinId") String pinId);
}
