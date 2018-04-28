package com.example.rookie.laminae.imageDetial;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rookie.laminae.API.ImageDetailAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.httpUtils.RetrofitClient;
import com.example.rookie.laminae.util.Base64;
import com.example.rookie.laminae.util.Constant;
import com.example.rookie.laminae.util.ImageLoadBuider;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ImageDetialActivity extends AppCompatActivity {
    private int pinsId;//需要加载的图片id
    private CollapsingToolbarLayout collToolbar;
    private ImageView collToolbarBackground;
    private TextView title;
    private TextView link;
    private TextView pins;//采集数量
    private TextView like;//喜欢数量
    private CircleImageView userIcon;
    private TextView username;
    private ImageView boardCoverOne;
    private ImageView boardCoverTwo;
    private ImageView boardCoverThree;
    private ImageView boardCoverFour;
    private TextView boardTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pinsId = getIntent().getIntExtra(Constant.PINSID,0);
        Log.d("imagedetail", "onCreate: "+pinsId);
        setContentView(R.layout.image_detial);
//      初始化控件
        collToolbarBackground = (ImageView) findViewById(R.id.header_image_background);
        title = (TextView) findViewById(R.id.image_detial_title);
        link = (TextView) findViewById(R.id.image_detial_link);
        pins = (TextView) findViewById(R.id.image_detial_pin);
        like = (TextView) findViewById(R.id.image_detial_like);
        userIcon = (CircleImageView) findViewById(R.id.image_detial_usericon);
        username = (TextView) findViewById(R.id.image_detial_username);
        boardCoverOne = (ImageView) findViewById(R.id.image_detial_board_cover_one);
        boardCoverTwo = (ImageView) findViewById(R.id.image_detial_board_cover_two);
        boardCoverThree = (ImageView) findViewById(R.id.image_detial_board_cover_three);
        boardCoverFour = (ImageView) findViewById(R.id.image_detial_board_cover_four);
        boardTitle = (TextView) findViewById(R.id.image_detial_board_title);
        setInfo();

    }

    /**
     * 根据网络请求结果设置界面信息
     */
    public void setInfo(){
        RetrofitClient client = RetrofitClient.getInstance();
        ImageDetailAPI imageDetialAPI = client.createService(ImageDetailAPI.class);
        Observable<PinsDetialBean> observable = imageDetialAPI.httpsPinsDetailRx(Base64.mClientInto,String.valueOf(pinsId));
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PinsDetialBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PinsDetialBean value) {
                        Log.d("image", "onNext: "+value.getPin().getBoard_id());
                        ImageLoadBuider.ImageLoadCenterCrop(getApplicationContext(),collToolbarBackground,value.getPin().getFile().getKey());
                        title.setText(value.getPin().getRaw_text());
                        link.setText(value.getPin().getLink());
                        pins.setText("采集 "+value.getPin().getRepin_count());
                        like.setText("喜欢 "+value.getPin().getLike_count());
                        ImageLoadBuider.ImageLoadCenterCrop(getApplicationContext(),userIcon,value.getPin().getUser().getAvatar().getKey());
                        username.setText(value.getPin().getUser().getUsername());
                        ImageLoadBuider.ImageLoadCenterCrop(getApplicationContext(),boardCoverOne,value.getPin().getBoard().getPins().get(0).getFile().getKey());
                        ImageLoadBuider.ImageLoadCenterCrop(getApplicationContext(),boardCoverTwo,value.getPin().getBoard().getPins().get(1).getFile().getKey());
                        ImageLoadBuider.ImageLoadCenterCrop(getApplicationContext(),boardCoverThree,value.getPin().getBoard().getPins().get(2).getFile().getKey());
                        ImageLoadBuider.ImageLoadCenterCrop(getApplicationContext(),boardCoverFour,value.getPin().getBoard().getPins().get(3).getFile().getKey());
                        boardTitle.setText(value.getPin().getBoard().getTitle());


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
