package com.example.rookie.laminae.main.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rookie.laminae.R;
import com.example.rookie.laminae.user.UserViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rookie on 2018/5/14.
 */

public class NewsFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private UserViewPagerAdapter myAdapter;
    private List<Fragment> fragments;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_main,container,false);
        fragments = new ArrayList<>();
        tabLayout = (TabLayout) view.findViewById(R.id.news_tab);
        viewPager = (ViewPager)view.findViewById(R.id.news_viewPager);
        String [] newsCategory = getActivity().getResources().getStringArray(R.array.news_category);
        for(int i=0;i<newsCategory.length;i++){
           fragments.add(NewsListFragment.newInstance(newsCategory[i]));
        }
        myAdapter = new UserViewPagerAdapter(getFragmentManager(),fragments);
        viewPager.setAdapter(myAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.removeAllTabs();
        for(int i=0;i<newsCategory.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(newsCategory[i]));
        }
        return view;
    }
}
