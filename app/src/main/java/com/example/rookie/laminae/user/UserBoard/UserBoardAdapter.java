package com.example.rookie.laminae.user.UserBoard;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.boarddetial.BoardDetialActivity;
import com.example.rookie.laminae.dialog.BoardEditDialog;
import com.example.rookie.laminae.util.Constant;
import com.example.rookie.laminae.util.ImageLoadBuider;

import java.util.List;

/**
 * Created by rookie on 2018/4/22.
 */

public class UserBoardAdapter extends RecyclerView.Adapter<UserBoardAdapter.MyViewHolder>{
    private List<UserBoardBean.BoardItemInfo> myBoards;
    private Context myContext;
    private String URL = "http://img.hb.aicdn.com/";
    private FragmentManager fragmentManager;
    private String boardId;
    private String boardTitle;
    private String boardDesciption;
    private boolean islogin;
    public UserBoardAdapter(List<UserBoardBean.BoardItemInfo> list,Context context,FragmentManager fragmentManager,boolean islogin){
        this.myBoards = list;
        this.myContext = context;
        this.fragmentManager = fragmentManager;
        this.islogin = islogin;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_board_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final UserBoardBean.BoardItemInfo boardInfo = myBoards.get(position);
        if(boardInfo.getPins().size()>0){
            ImageLoadBuider.ImageLoadFromParamsGeneral(myContext,holder.boardCover,boardInfo.getPins().get(0).getFile().getKey());
        }
        holder.boardCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, BoardDetialActivity.class);
                intent.putExtra(Constant.BOARDID,boardInfo.getBoard_id());
                intent.putExtra(Constant.BOARDTITLE,boardInfo.getTitle());
                intent.putExtra(Constant.BOARDDESCRIPTION,boardInfo.getDescription());
                myContext.startActivity(intent);
            }
        });

        boardId = String.valueOf(boardInfo.getBoard_id());
        boardTitle = boardInfo.getTitle();
        boardDesciption = boardInfo.getDescription();
        holder.boardTitle.setText(boardInfo.getTitle());
        holder.boardFollow.setText("采集 "+boardInfo.getPin_count()+"关注 "+boardInfo.getFollow_count());
        if(islogin){
            holder.boardOperate.setText("画板编辑");
        }else {
            holder.boardOperate.setVisibility(View.INVISIBLE);
        }

        holder.boardOperate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  showDialog();
            }
        });

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
    public void showDialog(){
        BoardEditDialog boardEditDialog = BoardEditDialog.newInstance(boardId,boardTitle,boardDesciption);
        boardEditDialog.show(fragmentManager,"boardEdit");
    }
}