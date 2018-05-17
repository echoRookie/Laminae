package com.example.rookie.laminae.setting;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.rookie.laminae.R;

public class MyPreferenceActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        /*设置toobar标题及默认返回键*/
        toolbar = (Toolbar) findViewById(R.id.preference_toolbar);
        toolbar.setTitle("用户设置");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        linearLayout = (LinearLayout)  findViewById(R.id.activity_preference);
        /*Fragment替换当前布局*/
        getFragmentManager().beginTransaction().replace(R.id.preference_main,new MyPreferenceFragment()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*系统默认返回键的点击事件*/
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
