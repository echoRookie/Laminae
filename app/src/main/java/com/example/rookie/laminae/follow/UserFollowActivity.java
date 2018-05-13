package com.example.rookie.laminae.follow;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.rookie.laminae.R;
import com.example.rookie.laminae.user.UserViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserFollowActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private UserViewPagerAdapter myAdapter;
    private FollowPinsFragment followPinsFragment;
    private FollowBoardFragment followBoardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        toolbar = (Toolbar) findViewById(R.id.result_toolbar);
        toolbar.setTitle("我的关注");
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setHomeButtonEnabled(true);
        }
        fragments = new ArrayList<>();
        followPinsFragment = new FollowPinsFragment();
        followBoardFragment = new FollowBoardFragment();
        fragments.add(followPinsFragment);
        fragments.add(followBoardFragment);
        tabLayout = (TabLayout) findViewById(R.id.result_tab);
        viewPager = (ViewPager) findViewById(R.id.result_viewPager);
        myAdapter = new UserViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(myAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.removeAllTabs();
        tabLayout.addTab(tabLayout.newTab().setText("关注的采集"));
        tabLayout.addTab(tabLayout.newTab().setText("关注的画板"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
