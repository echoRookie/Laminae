package com.example.rookie.laminae.base;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * Created by rookie on 2018/3/20.
 */

public class BaseApplication extends Application{
    private static Context myContext;

    @Override
    public void onCreate() {

        myContext = getApplicationContext();
        LitePal.initialize(myContext);
        super.onCreate();
    }
    public static Context getContext(){
        return myContext;
    }
}
