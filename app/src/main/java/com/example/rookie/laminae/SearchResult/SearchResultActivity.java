package com.example.rookie.laminae.searchResult;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.rookie.laminae.R;
import com.example.rookie.laminae.user.UserViewPagerAdapter;
import com.example.rookie.laminae.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
    private List<Fragment> fragments;
    private UserViewPagerAdapter myAdapter;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String searchKey;//搜索字段

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
//      得到搜索字段
        searchKey = getIntent().getStringExtra(Constant.SEARCHKEY);
        toolbar = (Toolbar) findViewById(R.id.result_toolbar);
        toolbar.setTitle("搜索"+searchKey+"的结果");
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        fragments = new ArrayList<>();
        fragments.add(PinsResultFragment.newInstance(searchKey));
        fragments.add(BoardResultFrgment.newInstance(searchKey));
        fragments.add(UserResultFragment.newInstance(searchKey));
        tabLayout = (TabLayout) findViewById(R.id.result_tab);
        viewPager = (ViewPager) findViewById(R.id.result_viewPager);

        myAdapter = new UserViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(myAdapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.removeAllTabs();
        tabLayout.addTab(tabLayout.newTab().setText("采集"));
        tabLayout.addTab(tabLayout.newTab().setText("画板"));
        tabLayout.addTab(tabLayout.newTab().setText("用户"));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /* 处理返回键的点击事件*/
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
