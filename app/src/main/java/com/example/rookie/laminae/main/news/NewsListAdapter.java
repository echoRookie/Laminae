package com.example.rookie.laminae.main.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rookie.laminae.R;
import com.example.rookie.laminae.entity.NewsDataDetial;

import java.util.List;

/**
 * Created by rookie on 2018/5/14.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder> {
    private List<NewsDataDetial> list;
    private Context context;

    public NewsListAdapter(List<NewsDataDetial> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.newsTitle.setText(list.get(position).title);
        if(list.get(position).user_info.name!=null){
            holder.newsUsername.setText(list.get(position).user_info.name);
        }

        holder.newsRecomment.setText(list.get(position).comment_count+"评论");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle;
        TextView newsUsername;
        TextView newsRecomment;
        public MyViewHolder(View itemView) {
            super(itemView);
            newsTitle = (TextView) itemView.findViewById(R.id.news_list_title);
            newsUsername = (TextView) itemView.findViewById(R.id.news_username);
            newsRecomment = (TextView) itemView.findViewById(R.id.news_comment_count);
        }
    }
}
