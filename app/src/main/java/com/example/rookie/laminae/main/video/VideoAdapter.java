package com.example.rookie.laminae.main.video;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.entity.Daily;
import com.example.rookie.laminae.util.ImageLoadBuider;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rookie on 2018/5/15.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
    private List<Daily.IssueList.ItemList> myList;
    private Context context;

    public VideoAdapter(List<Daily.IssueList.ItemList> myList, Context context) {
        this.myList = myList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mian_video_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Daily.IssueList.ItemList itemList = myList.get(position+1);
        Glide.with(context)
                .load(itemList.data.cover.feed)
                .centerCrop()
                .crossFade(1000)
                .into(holder.videoCover);
        Glide.with(context)
                .load(itemList.data.author.icon)
                .centerCrop()
                .crossFade(1000)
                .into(holder.videoUserIcon);
        holder.videoTitle.setText(itemList.data.title);
        holder.videoUserName.setText(itemList.data.author.name);
        holder.videoCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,VideoDetailActivity.class));
            }
        });


    }

    @Override
    public int getItemCount() {
        return myList.size()-1;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView videoCover;
        CircleImageView videoUserIcon;
        TextView videoTitle;
        TextView videoUserName;
        public MyViewHolder(View itemView) {
            super(itemView);
            videoCover = (ImageView) itemView.findViewById(R.id.video_cover);
            videoUserIcon = (CircleImageView) itemView.findViewById(R.id.video_usericon);
            videoTitle = (TextView) itemView.findViewById(R.id.video_title);
            videoUserName = (TextView) itemView.findViewById(R.id.video_username);
        }
    }
}
