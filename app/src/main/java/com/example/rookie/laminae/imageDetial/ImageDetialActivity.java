package com.example.rookie.laminae.imagedetial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rookie.laminae.api.DownUpAPI;
import com.example.rookie.laminae.api.ImageDetailAPI;
import com.example.rookie.laminae.api.OperateAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.dialog.GatherPinsDialog;
import com.example.rookie.laminae.entity.LikePinsOperateBean;
import com.example.rookie.laminae.httputils.RetrofitClient;
import com.example.rookie.laminae.main.home.ScrollListener;
import com.example.rookie.laminae.user.UserLike.UserLikeAdapter;
import com.example.rookie.laminae.user.UserPins.UserPinsBean;
import com.example.rookie.laminae.util.Base64;
import com.example.rookie.laminae.util.Constant;
import com.example.rookie.laminae.util.FileUtils;
import com.example.rookie.laminae.util.ImageLoadBuider;
import com.example.rookie.laminae.util.SPUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ImageDetialActivity extends AppCompatActivity {
    private PinsDetialBean pinsDetialBean;
    private String pinsKey;//需要加载图片的key；
    private int pinsId;//需要加载的图片id
    private CollapsingToolbarLayout collToolbar;
    private ImageView collToolbarBackground;
    private TextView title;
    private TextView link;
    private TextView pins;//采集数量
    private TextView like;//喜欢数量

    public int getPinsId() {
        return pinsId;
    }

    public void setPinsId(int pinsId) {
        this.pinsId = pinsId;
    }

    public String getPinsDescription() {
        return pinsDescription;
    }

    public void setPinsDescription(String pinsDescription) {
        this.pinsDescription = pinsDescription;
    }

    private CircleImageView userIcon;
    private TextView username;
    private ImageView boardCoverOne;
    private ImageView boardCoverTwo;
    private ImageView boardCoverThree;
    private ImageView boardCoverFour;
    private TextView boardTitle;
    private RecyclerView recyclerView;
    private int pageCount = 1;//统计当前的请求推荐图片的页数
    private UserLikeAdapter myAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<UserPinsBean.UserPinsItem> recommendPins;
    private NestedScrollView nestedScrollView;
    private Toolbar toolbar;
    private boolean is_like;
    private String pinsDescription;
    private FloatingActionButton pinsButton;//采集按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      将状态栏设为透明
        if(Build.VERSION.SDK_INT>=21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        pinsId = getIntent().getIntExtra(Constant.PINSID,0);
        Log.d("imagedetail", "onCreate: "+pinsId);
        setContentView(R.layout.image_detial);
//      初始化布局管理器
        gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
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
        recyclerView = (RecyclerView) findViewById(R.id.image_detial_recyclerView);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        toolbar = (Toolbar) findViewById(R.id.user_toolbar);
        pinsButton = (FloatingActionButton) findViewById(R.id.image_detial_floatButton);
        pinsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        setSupportActionBar(toolbar);
        setInfo(pinsId);
        getPinsRecommendFirst();
        recyclerView.addOnScrollListener(new ScrollListener((GridLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore() {
                getPinsRecommendScroll();
            }
        });






    }

    /**
     * 根据网络请求结果设置界面信息
     */
    public void setInfo(int id){
        RetrofitClient client = RetrofitClient.getInstance();
        ImageDetailAPI imageDetialAPI = client.createService(ImageDetailAPI.class);
        Observable<PinsDetialBean> observable = imageDetialAPI.httpsPinsDetailRx((String) SPUtils.get(this,Constant.USERAUthorization,Base64.mClientInto),String.valueOf(id));
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PinsDetialBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PinsDetialBean value) {
                        pinsKey = value.getPin().getFile().getKey();
                        pinsDetialBean = value;
                        pinsDescription = value.getPin().getRaw_text();
                        Log.d("image", "onNext: "+value.getPin().getBoard_id());
                        ImageLoadBuider.ImageLoadFromParamsGeneral(getApplicationContext(),collToolbarBackground,value.getPin().getFile().getKey());
                        title.setText(value.getPin().getRaw_text());
                        link.setText(value.getPin().getLink());
                        pins.setText("采集 "+value.getPin().getRepin_count());
                        like.setText("喜欢 "+value.getPin().getLike_count());
                        ImageLoadBuider.ImageLoadCenterCrop(getApplicationContext(),userIcon,value.getPin().getUser().getAvatar().getKey());
                        username.setText(value.getPin().getUser().getUsername());
                        if(value.getPin().getBoard().getPins().size()>=4) {
                            ImageLoadBuider.ImageLoadCenterCrop(getApplicationContext(), boardCoverOne, value.getPin().getBoard().getPins().get(0).getFile().getKey());
                            ImageLoadBuider.ImageLoadCenterCrop(getApplicationContext(), boardCoverTwo, value.getPin().getBoard().getPins().get(1).getFile().getKey());
                            ImageLoadBuider.ImageLoadCenterCrop(getApplicationContext(), boardCoverThree, value.getPin().getBoard().getPins().get(2).getFile().getKey());
                            ImageLoadBuider.ImageLoadCenterCrop(getApplicationContext(), boardCoverFour, value.getPin().getBoard().getPins().get(3).getFile().getKey());
                        }
                        is_like = value.getPin().isLiked();
                        Log.d("imagedddd", "onNext: "+is_like);
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

    /**
     *得到某个图片的推荐列表
     */
    public void getPinsRecommendFirst(){
        RetrofitClient client = RetrofitClient.getInstance();
        ImageDetailAPI im = client.createService(ImageDetailAPI.class);
        Observable<List<UserPinsBean.UserPinsItem>> o = im.httpPinsRecommendRx(Base64.mClientInto,String.valueOf(pinsId),pageCount,40);
        o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserPinsBean.UserPinsItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<UserPinsBean.UserPinsItem> value) {

                        Log.d("imagede", "onNextaa: "+value.size());
                        recommendPins = new ArrayList<UserPinsBean.UserPinsItem>();
                        for(int i= 0;i<value.size();i++){
                            recommendPins.add(value.get(i));
                        }
                        myAdapter = new UserLikeAdapter(recommendPins,getApplicationContext());
                        recyclerView.setAdapter(myAdapter);
                        ++ pageCount;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }

    /**
     * 推荐列表的后续滑动
     */
    public void getPinsRecommendScroll(){
        RetrofitClient client = RetrofitClient.getInstance();
        ImageDetailAPI im = client.createService(ImageDetailAPI.class);
        Observable<List<UserPinsBean.UserPinsItem>> o = im.httpPinsRecommendRx(Base64.mClientInto,String.valueOf(pinsId),pageCount,40);
        o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserPinsBean.UserPinsItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<UserPinsBean.UserPinsItem> value) {
                        Log.d("imagede3", "onNextaaa: "+value.size());

                        for(int i= 0;i<value.size();i++){
                            recommendPins.add(value.get(i));
                        }
                       myAdapter.notifyDataSetChanged();
                        pageCount ++;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.image_detial_menu,menu);
        return true;
    }

    /**
     * toolbar的菜单选中操作
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_like:
                userOperateLike();
                break;
            case R.id.action_download:
                if(ContextCompat.checkSelfPermission(ImageDetialActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager
                        .PERMISSION_GRANTED) {
                    Log.d("zzzzzzzzzzzzzz", "onCreate: "+"没有");
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);

                }
                else {
                    Log.d("zzzzzzzzzzzzzzz", "onCreate: "+"有");
                }

                userOperateDownLoad();
                this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,

                        Uri.fromFile(new File(FileUtils.getDirsFile().getPath()))));
                break;



        }
        return true;
    }
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    /**
     * 用户对图片进行like操作
     */
    private void userOperateLike(){
        Toast.makeText(this, "like", Toast.LENGTH_SHORT).show();
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        OperateAPI operateAPI = retrofitClient.createService(OperateAPI.class);
        String m = (String) SPUtils.get(this, Constant.USERAUthorization, Base64.mClientInto);
        Observable<LikePinsOperateBean> observable = operateAPI.httpsLikeOperate(m, String.valueOf(pinsId), "like");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LikePinsOperateBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LikePinsOperateBean value) {
                        is_like = !is_like;
                        if (is_like)
                            toolbar.getMenu().findItem(R.id.action_like).setTitle("已添加到喜欢");
                        if (!is_like)
                            toolbar.getMenu().findItem(R.id.action_like).setTitle("喜欢");

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void userOperateDownLoad(){
        Toast.makeText(this,"下载",Toast.LENGTH_SHORT).show();
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        DownUpAPI downUpAPI = retrofitClient.createService(DownUpAPI.class);
        Observable<ResponseBody> observable = downUpAPI.httpDownImage(pinsKey);
        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody value) {
                        FileUtils.writeResponseBodyToDisk(FileUtils.getDirsFile(),value,String.valueOf(pinsId)+".jpg");
                        Log.d("loadsuccess", "onNext: "+value.toString());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("loadd", "onError: "+e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       switch (requestCode){
           case 1:
               if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                   File file = FileUtils.getDirsFile();
                   if (file.exists()){
                       Log.d("loaddd", "onOptionsItemSelected: "+"存在");
                   }
                   else{
                       Log.d("loadddddd", "onOptionsItemSelected: "+"不存在");
                   }
               }
       }
    }

    /**
     * 展示采集对话框
     */
    public void showDialog(){
        GatherPinsDialog gatherPinsDialog = new GatherPinsDialog();
        gatherPinsDialog.show(getSupportFragmentManager(),"gatherPinsDialog");
    }
}
