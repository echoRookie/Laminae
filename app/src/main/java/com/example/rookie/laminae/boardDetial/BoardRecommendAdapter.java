package com.example.rookie.laminae.boardDetial;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rookie.laminae.R;
import com.example.rookie.laminae.entity.PinsMainEntity;
import com.example.rookie.laminae.util.ImageLoadBuider;

import java.util.List;

/**
 * Created by rookie on 2018/5/10.
 */

public class BoardRecommendAdapter extends RecyclerView.Adapter<BoardRecommendAdapter.MyViewHolder> {
    private List<PinsMainEntity> myList;
    private Context myContext;

    public BoardRecommendAdapter(List<PinsMainEntity> myList, Context myContext) {
        this.myList = myList;
        this.myContext = myContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_detial_recommend,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PinsMainEntity pinsMainEntity = myList.get(position);
        ImageLoadBuider.ImageLoadCenterCrop(myContext,holder.cover,pinsMainEntity.getFile().getKey());
        holder.text.setText(pinsMainEntity.getRaw_text());
        holder.text.setText("喜欢"+pinsMainEntity.getLike_count()+"采集"+pinsMainEntity.getRepin_count());

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView text;
        TextView follow;
        public MyViewHolder(View itemView) {
            super(itemView);
            cover = (ImageView) itemView.findViewById(R.id.board_detial_recommend_cover);
            text = (TextView) itemView.findViewById(R.id.board_detial_recommend_text);
            follow = (TextView) itemView.findViewById(R.id.board_detial_recommend_follow);
        }
    }
}
