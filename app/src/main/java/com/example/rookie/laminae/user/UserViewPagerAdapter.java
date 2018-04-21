package com.example.rookie.laminae.user;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rookie on 2018/4/20.
 * 用户的个人信息适配器
 */

public class UserViewPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> myFragment;

    public UserViewPagerAdapter(FragmentManager fm, List<Fragment> fragment) {
        super(fm);
        myFragment = fragment;
    }
    @Override
    public Fragment getItem(int position) {
        return myFragment.get(position);
    }

    @Override
    public int getCount() {
        return myFragment.size();
    }

}
