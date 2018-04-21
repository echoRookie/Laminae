package com.example.rookie.laminae.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.target.Target;
import com.example.rookie.laminae.API.DownUpAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.httpUtils.RetrofitClient;

import java.util.concurrent.ExecutionException;

/**
 * Created by rookie on 2018/4/17.
 */

public class ImageLoadBuider {

    /**
     * 图片加载地址
     * 分为大中小
     */

    /*
    private static String url_general = Resources.getSystem().getString(R.string.url_image_general);
    private static String url_samll = Resources.getSystem().getString(R.string.url_image_small) ;
    private static String url_big = Resources.getSystem().getString(R.string.url_image_big);
    private static String url = Resources.getSystem().getString(R.string.url_image) ;
    private static Context loadContext;
    private static ImageView loadView;
    private static String loadKey;

    public ImageLoadBuider(Context context,ImageView view,String string){
        this.loadContext = context;
        this.loadView = view;
        this.loadKey = string;

    }*/
    public static void ImageLoadfitCenter(Context context,ImageView view,String string){
        String url= "http://img.hb.aicdn.com/";
        Glide.with(context)
                .load(url+string)
                .into(view);




    }
    public static void ImageLoadCenterCrop(){

    }
    public static void ImageFromParams(){}
//    public static Bitmap ImageAsBitmap(final Context context,final Bitmap bitmap){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    bitmap = Glide.with(context)
//                            .load(R.string.url_image+"654953460733026a7ef6e101404055627ad51784a95c-B6OFs4")
//                            .asBitmap()
//                            .into(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
//                            .get();
//
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//        return bitmap;
//
//    }
}
