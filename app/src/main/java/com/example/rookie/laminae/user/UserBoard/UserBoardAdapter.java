package com.example.rookie.laminae.user.UserBoard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rookie.laminae.R;

import java.util.List;

/**
 * Created by rookie on 2018/4/22.
 */

public class UserBoardAdapter extends RecyclerView.Adapter<UserBoardAdapter.MyViewHolder>{
    private List<UserBoardBean.BoardItemInfo> myBoards;
    private Context myContext;
    private String URL = "http://img.hb.aicdn.com/";
    public UserBoardAdapter(List<UserBoardBean.BoardItemInfo> list,Context context){
        this.myBoards = list;
        this.myContext = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_board_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UserBoardBean.BoardItemInfo boardInfo = myBoards.get(position);
        Glide.with(myContext)
                .load(URL+boardInfo.getPins().get(0).getFile().getKey())
                .crossFade(1000)
                .centerCrop()
                .into(holder.boardCover);
        holder.boardTitle.setText(boardInfo.getTitle());
        holder.boardFollow.setText("采集 "+boardInfo.getPin_count()+"关注 "+boardInfo.getFollow_count());
        holder.boardOperate.setText("画板编辑");

    }

    @Override
    public int getItemCount() {
        return myBoards.size();
    }

    public class MyViewHolder extends RecyclerView. ViewHolder{
        ImageView boardCover;
        TextView boardTitle;
        TextView boardFollow;
        TextView boardOperate;
        public MyViewHolder(View itemView) {
            super(itemView);
            boardCover = (ImageView) itemView.findViewById(R.id.user_board_cover);
            boardTitle = (TextView) itemView.findViewById(R.id.user_board_title);
            boardFollow = (TextView) itemView.findViewById(R.id.user_board_follow);
            boardOperate = (TextView)itemView.findViewById(R.id.user_board_operate);
        }
    }
}