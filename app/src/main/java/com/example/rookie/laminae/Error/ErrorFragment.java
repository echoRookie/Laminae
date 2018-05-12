package com.example.rookie.laminae.error;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rookie.laminae.R;

/**
 * Created by rookie on 2018/5/7.
 * 加载错误替换页面
 */

public class ErrorFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_error,container,false);
        return view;
    }
}
