package com.example.rookie.laminae.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by rookie on 2018/4/17.
 */

public class ImageLoadBuider {

    /**
     * 图片加载地址
     * 分为大中小
     * @param context
     * @param view
     * @param string
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
                .fitCenter()
                .crossFade(1000)
                .into(view);




    }

    /**加载中心填充图片
     * @param context
     * @param view
     * @param string
     */
    public static void ImageLoadCenterCrop(Context context,ImageView view,String string){
        String url= "http://img.hb.aicdn.com/";
        Glide.with(context)
                .load(url+string)
                .centerCrop()
                .crossFade(1000)
                .into(view);

    }

    /**
     * 加载中心填充+高斯模糊
     * @param context
     * @param view
     * @param string
     */
    public static void ImageLoadCenterCropBlur(Context context,ImageView view,String string){
        String url= "http://img.hb.aicdn.com/";
        Glide.with(context)
                .load(url+string)
                .centerCrop()
                .bitmapTransform()
                .crossFade(1000)
                .into(view);

    }

    /**按尺寸加载大中小图
     * @param width
     * @param height
     * @param context
     * @param view
     * @param string
     */
    public static void ImageLoadFromParamsBig(int width,int height,Context context,ImageView view,String string){
        String url= "http://img.hb.aicdn.com/";
        String image_big = "_fw658";
        Glide.with(context)
                .load(url+string+image_big)
                .override(width,height)
                .centerCrop()
                .crossFade(1000)
                .into(view);
    }
    public static void ImageLoadFromParamsGeneral(Context context,ImageView view,String string){
        String url= "http://img.hb.aicdn.com/";
        String image_general = "_fw320sf";
        Glide.with(context)
                .load(url+string+image_general)
                .centerCrop()
                .crossFade(1000)
                .into(view);
    }
    public static void ImageLoadFromParamsSmall(Context context,ImageView view,String string){
        String url= "http://img.hb.aicdn.com/";
        String image_small = "_sq75sf";
        Glide.with(context)
                .load(url+string+image_small)
                .centerCrop()
                .crossFade(1000)
                .into(view);
    }
    public static void ImageLoadFromParams(Context context,ImageView view,String string){
        String url= "http://img.hb.aicdn.com/";
        Glide.with(context)
                .load(url+string)
                .centerCrop()
                .crossFade(1000)
                .into(view);
    }

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
