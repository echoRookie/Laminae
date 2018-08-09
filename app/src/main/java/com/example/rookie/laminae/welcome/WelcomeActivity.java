package com.example.rookie.laminae.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.rookie.laminae.R;
import com.example.rookie.laminae.main.MainActivity;
import com.example.rookie.laminae.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    private ImageView imageView;
    private List<Integer> drawableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        imageView = (ImageView) findViewById(R.id.welcome_image);
        SharedPreferences preferences = android.preference.PreferenceManager.getDefaultSharedPreferences(this);
        Boolean wallpaper = preferences.getBoolean("switch_wallpaper",false);
        Log.d("fffffff", "onCreate: "+wallpaper);
//      如果设置是成立时，开启壁纸切换
        if(wallpaper){
            drawableList  = new ArrayList<>();
            drawableList.add(R.drawable.welcome_image1);
            drawableList.add(R.drawable.welcome_image2);
            drawableList.add(R.drawable.welcome_image3);
            drawableList.add(R.drawable.welcome_image4);
            int number=(int)(Math.random()*4);

            imageView.setImageResource(drawableList.get(number));
        }

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.enlarge);
        animation.setFillAfter(true);
        imageView.startAnimation(animation);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();

            }
        },2000);
    }
}
