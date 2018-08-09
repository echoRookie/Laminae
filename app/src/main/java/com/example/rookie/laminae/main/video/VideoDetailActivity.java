package com.example.rookie.laminae.main.video;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.util.Constant;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;


public class VideoDetailActivity extends AppCompatActivity {
    private String pageUrl;//下一页加载的网址
    private String videoDescription;//描述
    private String videoBlur;//背景模糊图
    private TextView description;//视频描述
    private ImageView blur;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_detail);
//       初始化标题
        toolbar = (Toolbar) findViewById(R.id.video_toolbar);
        toolbar.setTitle("视频播放");
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        pageUrl = getIntent().getStringExtra(Constant.PLAYURL);
        videoDescription = getIntent().getStringExtra(Constant.VIDEODESCRIPTION);
        videoBlur = getIntent().getStringExtra(Constant.VIDEOBLUR);
        description = (TextView) findViewById(R.id.video_description);
        blur = (ImageView) findViewById(R.id.video_background) ;
        description.setText(videoDescription);
        Glide.with(this)
                .load(videoBlur)
                .centerCrop()
                .into(blur);
        JZVideoPlayerStandard jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.videoplayer);
        jzVideoPlayerStandard.setUp(pageUrl, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");

    }
    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
//  toolbar点击返回
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
