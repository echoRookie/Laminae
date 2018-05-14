package com.example.rookie.laminae.main.classify;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rookie.laminae.R;
import com.example.rookie.laminae.util.Constant;
import com.example.rookie.laminae.util.ImageLoadBuider;

import java.util.List;

/**
 * Created by rookie on 2018/4/26.
 */

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.MyViewHolder> {
    private List<String> myTitles;
    private List<String> myTypes;
    private Context myContext;
    private List<String> myKeys;//类别图片的前三张id
    private int pinsLenght;
    private int keysLenght;
    private String URL = "http://img.hb.aicdn.com/";

    /**
     * 构造函数
     */
    public ClassifyAdapter(List<String> titles,List<String> keys,List<String> types,Context context){
        this.myTitles = titles;
        this.myKeys = keys;
        this.myTypes = types;
        this.myContext = context;
        pinsLenght = keys.size()/3;
        keysLenght = keys.size();
        Log.d("classs", "ClassifyAdapter: "+pinsLenght+""+keysLenght);

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_classify_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.classifyTitle.setText(myTitles.get(position));
        int index = position + 1;
//      进行网络请求获取各个类别的数据
        ImageLoadBuider.ImageLoadFromParamsGeneral(myContext,holder.classifyCoverOne,myKeys.get(keysLenght*index/pinsLenght-3));
        ImageLoadBuider.ImageLoadFromParamsGeneral(myContext,holder.classifyCoverTwo,myKeys.get(keysLenght*index/pinsLenght-2));
        ImageLoadBuider.ImageLoadFromParamsGeneral(myContext,holder.classifyCoverThree,myKeys.get(keysLenght*index/pinsLenght-1));
        holder.classifyCoverOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext,ClassifyInfoActivity.class);
                intent.putExtra(Constant.TYPEKEY,myTypes.get(position));
                intent.putExtra(Constant.TYPETITLE,myTitles.get(position));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return myTitles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView classifyTitle;
        ImageView classifyCoverOne;
        ImageView classifyCoverTwo;
        ImageView classifyCoverThree;
        public MyViewHolder(View itemView) {
            super(itemView);
            classifyTitle = (TextView) itemView.findViewById(R.id.classify_title);
            classifyCoverOne = (ImageView) itemView.findViewById(R.id.classify_cover_one);
            classifyCoverTwo = (ImageView) itemView.findViewById(R.id.classify_cover_two);
            classifyCoverThree = (ImageView) itemView.findViewById(R.id.classify_cover_three);
        }
    }
}
