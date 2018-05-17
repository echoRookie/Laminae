package com.example.rookie.laminae.main.video;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private TextView description;
    private ImageView blur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_detail);
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
}
