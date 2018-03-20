package com.example.rookie.laminae.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by rookie on 2018/3/20.
 */

public class BaseApplication extends Application{
    private static Context myContext;

    @Override
    public void onCreate() {
        super.onCreate();
        myContext = getApplicationContext();
    }
    public static Context getContext(){
        return myContext;
    }
}
