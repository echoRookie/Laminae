package com.example.rookie.laminae.setting;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.example.rookie.laminae.R;


/**
 * Created by rookie on 2017/6/14.
 */

public class MyPreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载设置布局
        addPreferencesFromResource(R.xml.setting_preference);
        //开启消息通知的点击事件
        findPreference("switch_notification").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Boolean result1= (Boolean)newValue ;
                Log.d("fffff", "onPreferenceClick: "+result1);
                //值为true则开启消息服务
                if (result1 == true){
//                    Intent intent = new Intent(getActivity(),NotificationService.class);
//                    getActivity().startService(intent);
                }
                //值为false则关闭消息服务
                else {
//                    Intent intent = new Intent(getActivity(),NotificationService.class);
//                    getActivity().stopService(intent);
                }
                return true;
            }
        });
        //        模式切换的点击事件
        findPreference("isNight").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Boolean result1= (Boolean)newValue ;
                Log.d("fffff", "onPreferenceClick: "+result1);

                return true;
            }
        });
    }
}
